import org.apache.shiro.SecurityUtils
import intient.nimble.domain.User

class EntryController {

  def scaffold = Entry

  def atom = {
    if(!params.max) params.max = 10
    def list = Entry.list( params )    
    def lastUpdated = list[0].lastUpdated
    println request.serverName
    
    [ entryInstanceList:list, lastUpdated:lastUpdated ]
  }

  def save = {
      def entryInstance = new Entry(params)
      entryInstance.author = authenticatedUser
      
      //handle uploaded file
      def uploadedFile = request.getFile('payload')
      if(!uploadedFile.empty){
        println "Class: ${uploadedFile.class}"
        println "Name: ${uploadedFile.name}"
        println "OriginalFileName: ${uploadedFile.originalFilename}"
        println "Size: ${uploadedFile.size}"
        println "ContentType: ${uploadedFile.contentType}"
        
        def webRootDir = servletContext.getRealPath("/")
        println webRootDir
        def userDir = new File(webRootDir, "/payload/${authenticatedUser.username}")
        userDir.mkdirs()
        uploadedFile.transferTo( new File( userDir, uploadedFile.originalFilename))               
        entryInstance.filename = uploadedFile.originalFilename
      }      
      
      if(!entryInstance.hasErrors() && entryInstance.save()) {
          flash.message = "Entry ${entryInstance.id} created"
          redirect(action:show,id:entryInstance.id)
      }
      else {
          render(view:'create',model:[entryInstance:entryInstance])
      }
  }

  //scaffolded code with authorization checks
  def edit = {
      def entryInstance = Entry.get( params.id )

      if(!SecurityUtils.getSubject().isPermitted('blog:entry:edit:' + entryInstance.id)) {
          response.sendError(403)
          return
      }

      if(!entryInstance) {
          flash.message = "Entry not found with id ${params.id}"
          redirect(action:list)
      }
      else {
          return [ entryInstance : entryInstance ]
      }
  }

  def delete = {
      def entryInstance = Entry.get( params.id )

      if(!SecurityUtils.getSubject().isPermitted('blog:entries:delete:' + entryInstance.id)) {
          response.sendError(403)
          return
      }

      if(entryInstance) {
          entryInstance.delete()
          flash.message = "Entry ${params.id} deleted"
          redirect(action:list)
      }
      else {
          flash.message = "Entry not found with id ${params.id}"
          redirect(action:list)
      }
  }



  
  def list = {
      if(!params.max)
      params.max = 10

      flash.title = params.title

      if(!params.title)
        params.title = ""

      def author = null
      if(params.id)
        author = User.get(params.id)

      def entryList
      def entryCount
      if(author){
        def query = { 
          and{
            eq('author', author.data)
            like("title", params.title.decodeUnderscore() + '%')
          }
        }  
        entryList = Entry.createCriteria().list(params, query)        
        entryCount = Entry.createCriteria().count(query)

      }else{
        entryList = Entry.list( max: params.max )
        entryCount = Entry.count()
      }
      
      [ entryInstanceList:entryList, entryCount:entryCount  ]
  }  
}