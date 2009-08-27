// locations to search for config files that get merged into the main config
// config files can either be Java properties files or ConfigSlurper scripts

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if(System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.mime.file.extensions = true // enables the parsing of file extensions from URLs into the request format
grails.mime.types = [ html: ['text/html','application/xhtml+xml'],
                      xml: ['text/xml', 'application/xml'],
                      text: 'text-plain',
                      js: 'text/javascript',
                      rss: 'application/rss+xml',
                      atom: 'application/atom+xml',
                      css: 'text/css',
                      csv: 'text/csv',
                      all: '*/*',
                      json: ['application/json','text/json'],
                      form: 'application/x-www-form-urlencoded',
                      multipartForm: 'multipart/form-data'
                    ]
// The default codec used to encode data with ${}
grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"
grails.converters.encoding="UTF-8"

// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true

// set per-environment serverURL stem for creating absolute links
environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
}

// log4j configuration
log4j = {
    error 'org.codehaus.groovy.grails.web.servlet',
          'org.codehaus.groovy.grails.web.pages',
          'org.codehaus.groovy.grails.web.sitemesh',
          'org.codehaus.groovy.grails.web.mapping.filter',
          'org.codehaus.groovy.grails.web.mapping',
          'org.codehaus.groovy.grails.commons',
          'org.codehaus.groovy.grails.plugins'

    error 'org.codehaus.groovy.grails.orm.hibernate',
          'org.hibernate'

    info 'org.springframework.beans'

    debug 'grails.app.controller',
          'grails.app.service',
          'grails.app.domain',
          'intient.nimble'
}

grails.app.context = "blogito"

environments {
    production {
        grails.serverURL = "http://www.changeme.com"
    }
    testing {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
    development {
        grails.serverURL = "http://localhost:8080/${appName}"
    }
}


     