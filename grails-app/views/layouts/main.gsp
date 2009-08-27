<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        
        <g:javascript library="application" />

        <n:basecss/>
        <n:jquery/>
        <n:userhighlight />

        <g:layoutHead />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${createLinkTo(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <g:render template="/layouts/header"/>
        <div style="padding: 18px;">
          <g:layoutBody />
        </div>
    </body>	
</html>