<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
  "http://www.w3.org/TR/html4/strict.dtd">

<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1"/>
    <title>Blogito | Secure Area | <g:layoutTitle default="Grails"/></title>

  <link rel="stylesheet" href="${createLinkTo(dir:'css',file:'main.css')}" />
  <link rel="shortcut icon" href="${createLinkTo(dir:'images',file:'favicon.ico')}" type="image/x-icon" />

  <n:jquery/>
  <n:growl/>
  <n:flashgrowl/>
  <n:menu/>
  <n:userhighlight/>
  <n:admin/>



  <g:layoutHead/>
</head>

<body>

  <div id="doc">
    <div id="hd">
      <g:render template="/layouts/header"/> 
      <g:render template='/templates/nimble/navigation/topnavigation'/>
    </div>
    <div id="bd">
      <g:layoutBody/>
    </div>
    <div id="ft">

    </div>
  </div>

<n:sessionterminated/>


</body>

</html>