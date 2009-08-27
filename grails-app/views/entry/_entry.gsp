<div class="entry">
  <span class="entry-date"><g:longDate>${entryInstance.lastUpdated}</g:longDate> : <span class="userhighlight user_${entryInstance.author.id}"><n:photo size="32" id="${entryInstance.author.id}"/>${entryInstance.author.profile.fullName}</span>
  <h2><g:link action="show" id="${entryInstance.id}">${entryInstance.title}</g:link></h2>                  
  <p>${entryInstance.summary}</p>    
    
  <g:displayFile filename="${entryInstance.filename}" user="${entryInstance.author.username}" />

  <n:hasPermission target="blog:entry:edit:${entryInstance.id}">
    <g:link controller="entry" action="edit" id="${entryInstance.id}">Edit Entry</g:link>
  </n:hasPermission>
              
</div>