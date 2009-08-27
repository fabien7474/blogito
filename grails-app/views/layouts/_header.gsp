<div id="header" class="clearfix">
  <div style="float:left;"
       <p><g:link class="header-main" controller="entry">Blogito</g:link></p>
    <p class="header-sub">
    <g:link controller="entry" action="atom">
      <img src="${createLinkTo(dir:'images',file:'feed-icon-28x28.png')}" alt="Subscribe" title="Subscribe"/>
    </g:link>
    A tiny little blog
    </p>
  </div>

  <div id="loginheader" style="float: right; text-align: right;">
    <p>
    <n:isNotLoggedIn>
      <h3><g:link controller="auth" action="login">Login</g:link></h3>
    </n:isNotLoggedIn>
    <n:isLoggedIn>
      <h3 style="margin: 6px;">Welcome back, <span class="userhighlight"><n:principalName/></span></h3>
      <h4><g:link controller="entry" action="create">New Post</g:link></h4>
      <h4><g:link controller="auth" action="logout">Logout</g:link></h4>
      <n:isAdministrator><g:link controller="admins">Administer Application</g:link></n:isAdministrator>
    </n:isLoggedIn>
    </p>
  </div>

</div>



<div style="clear:left;"></div>