<#-- @ftlvariable name="token" type="ru.myproject.first_project.domain.DTO.ResetPasswordTokenDTO" -->
<#-- @ftlvariable name="loginForm" type="ru.myproject.first_project.domain.UserCredentials" -->
<#-- @ftlvariable name="registerForm" type="ru.myproject.first_project.domain.User" -->
<#-- @ftlvariable name="name" type="String" -->
<#-- @ftlvariable name="user" type="ru.myproject.first_project.domain.User" -->
<#import "/spring.ftl" as spring>
<#macro navigation>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-static-top" role="navigation">
        <div class="container">
            <!-- Logo and responsive toggle -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="\index"><span class="glyphicon glyphicon-globe"></span> Logo</a>
            </div>
            <!-- Navbar links -->
            <div class="collapse navbar-collapse" id="navbar">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="/about">About</a>
                    </li>
                    <li>
                        <a href="/products">Products</a>
                    </li>


                </ul>
                <div class="navbar-right">
                    <#if user?has_content == false>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="/enter">Sign in</a>
                            </li>
                            <li>
                                <a href="register">Sign up</a>
                            </li>
                        </ul>
                    <#else>
                        <ul class="nav navbar-nav">
                            <li>
                                <a href="/personalAccount">${user.surname!} ${user.name!}</a>
                            </li>
                            <li>
                                <a href="/logout">Logout</a>
                            </li>
                        </ul>
                    </#if>
                </div>
                <!-- Search -->
                <form class="navbar-form navbar-right" role="search">
                    <div class="form-group">
                        <label>
                            <input name="searchForm" type="text" class="form-control">
                        </label>
                    </div>
                    <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span>
                        Search
                    </button>
                </form>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
</#macro>

<#macro footer>
    <footer>
        <div class="footer-blurb">
            <div class="container">
                <div class="row">
                    <div class="col-sm-3 footer-blurb-item">
                        <h3><span class="glyphicon glyphicon-text-size"></span> Dynamic</h3>
                        <p>Collaboratively administrate empowered markets via plug-and-play networks. Dynamically
                            procrastinate B2C users after installed base benefits. Dramatically visualize customer
                            directed convergence without revolutionary ROI.</p>
                        <p><a class="btn btn-default" href="#">Procrastinate</a></p>
                    </div>
                    <div class="col-sm-3 footer-blurb-item">
                        <h3><span class="glyphicon glyphicon-wrench"></span> Efficient</h3>
                        <p>Dramatically maintain clicks-and-mortar solutions without functional solutions. Efficiently
                            unleash cross-media information without cross-media value. Quickly maximize timely
                            deliverables for real-time schemas. </p>
                        <p><a class="btn btn-default" href="#">Unleash</a></p>
                    </div>
                    <div class="col-sm-3 footer-blurb-item">
                        <h3><span class="glyphicon glyphicon-paperclip"></span> Complete</h3>
                        <p>Professionally cultivate one-to-one customer service with robust ideas. Completely synergize
                            resource taxing relationships via premier niche markets. Dynamically innovate
                            resource-leveling customer service for state of the art customer service.</p>
                        <p><a class="btn btn-default" href="#">Complete</a></p>
                    </div>
                    <div class="col-sm-3 footer-blurb-item">

                        <!-- Thumbnails -->
                        <h3><span class="glyphicon glyphicon-camera"></span> Phosfluorescent</h3>
                        <div class="row">
                            <div class="col-xs-6">
                                <a href="#" class="thumbnail">
                                    <img src="holder.js/110x80" alt="">
                                </a>
                            </div>
                            <div class="col-xs-6">
                                <a href="#" class="thumbnail">
                                    <img src="holder.js/110x80" alt="">
                                </a>
                            </div>
                            <div class="col-xs-6">
                                <a href="#" class="thumbnail">
                                    <img src="holder.js/110x80" alt="">
                                </a>
                            </div>
                            <div class="col-xs-6">
                                <a href="#" class="thumbnail">
                                    <img src="holder.js/110x80" alt="">
                                </a>
                            </div>
                        </div>

                    </div>

                </div>
                <!-- /.row -->
            </div>
        </div>

        <div class="small-print">
            <div class="container">
                <p><a href="#">Terms &amp; Conditions</a> | <a href="#">Privacy Policy</a> | <a href="#">Contact</a></p>
                <p>Copyright &copy; Alyokhin Artyom 2020</p>
            </div>
        </div>
    </footer>
</#macro>
<#macro mainBody>
    <!-- Alert -->
    <div class="alert alert-success alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span
                    aria-hidden="true">&times;</span>
        </button>
        <strong>Synergize:</strong> Seamlessly visualize quality intellectual capital!
    </div>

    <!-- Articles -->
    <div class="row">
        <article class="col-xs-12">
            <h2>Premier Niche Markets</h2>
            <p>Phosfluorescently engage worldwide methodologies with web-enabled technology. Interactively
                coordinate proactive e-commerce via process-centric "outside the box" thinking. Completely
                pursue scalable customer service through sustainable potentialities.</p>
            <p>
                <button class="btn btn-default">Read More</button>
            </p>
            <p class="pull-right"><span class="label label-default">keyword</span> <span
                        class="label label-default">tag</span> <span class="label label-default">post</span></p>
            <ul class="list-inline">
                <li><a href="#">Today</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-comment"></span> 2 Comments</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-share"></span> 8 Shares</a></li>
            </ul>
        </article>
    </div>
    <hr>
    <div class="row">
        <article class="col-xs-12">
            <h2>Proactively Envisioned</h2>
            <p>Seamlessly visualize quality intellectual capital without superior collaboration and
                idea-sharing. Holistically pontificate installed base portals after maintainable products.
                Proactively envisioned multimedia based expertise and cross-media growth strategies.</p>
            <p>
                <button class="btn btn-default">Read More</button>
            </p>
            <p class="pull-right"><span class="label label-default">keyword</span> <span
                        class="label label-default">tag</span> <span class="label label-default">post</span></p>
            <ul class="list-inline">
                <li><a href="#">Yesterday</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-comment"></span> 21 Comments</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-share"></span> 36 Shares</a></li>
            </ul>
        </article>
    </div>
    <hr>
    <div class="row">
        <article class="col-xs-12">
            <h2>Completely Synergize</h2>
            <p>Completely synergize resource taxing relationships via premier niche markets. Professionally
                cultivate one-to-one customer service with robust ideas. Dynamically innovate resource-leveling
                customer service for state of the art customer service.</p>
            <p>
                <button class="btn btn-default">Read More</button>
            </p>
            <p class="pull-right"><span class="label label-default">keyword</span> <span
                        class="label label-default">tag</span> <span class="label label-default">post</span></p>
            <ul class="list-inline">
                <li><a href="#">2 Days Ago</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-comment"></span> 12 Comments</a></li>
                <li><a href="#"><span class="glyphicon glyphicon-share"></span> 18 Shares</a></li>
            </ul>
        </article>
    </div>
    <hr>
</#macro>
<#macro page>
    <@specialPage>
        <div class="container-fluid">
            <!-- Left Column -->
            <@leftPanel/>
            <div class="col-sm-6">
                <#nested/>
            </div>
            <@rightPanel/>
        </div>
    </@specialPage>
</#macro>
<#macro rightPanel>
    <!-- Right Column -->
    <div class="col-sm-3">
        <!-- Progress Bars -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-scale"></span>
                    Dramatically Engage
                </h3>
            </div>
            <div class="panel-body">
                <div class="progress">
                    <div class="progress-bar progress-bar-success" role="progressbar" aria-valuenow="100"
                         aria-valuemin="0" aria-valuemax="100" style="width:100%">
                        100% Proactively Envisioned
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="80"
                         aria-valuemin="0" aria-valuemax="100" style="width:80%">
                        80% Objectively Innovated
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="45"
                         aria-valuemin="0" aria-valuemax="100" style="width:45%">
                        45% Portalled
                    </div>
                </div>
                <div class="progress">
                    <div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="35"
                         aria-valuemin="0" aria-valuemax="100" style="width:35%">
                        35% Done
                    </div>
                </div>
            </div>
        </div>

        <!-- Carousel -->
        <h3><span class="glyphicon glyphicon-modal-window"></span> Synergization</h3>
        <div id="side-carousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#side-carousel" data-slide-to="0" class="active"></li>
                <li data-target="#side-carousel" data-slide-to="1"></li>
                <li data-target="#side-carousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <a href="#">
                        <img class="img-responsive" src="holder.js/400x300?bg=FF8C00&text=Image%201" alt="">
                    </a>
                    <div class="carousel-caption">
                        <h3>Dramatically Engage</h3>
                        <p>Objectively innovate empowered manufactured products whereas parallel platforms.</p>
                    </div>
                </div>
                <div class="item">
                    <a href="#">
                        <img class="img-responsive" src="holder.js/400x300?bg=FF8C00&text=Image%202" alt="">
                    </a>
                    <div class="carousel-caption">
                        <h3>Efficiently Unleash</h3>
                        <p>Dramatically maintain clicks-and-mortar solutions without functional solutions.</p>
                    </div>
                </div>
                <div class="item">
                    <a href="#">
                        <img class="img-responsive" src="holder.js/400x300?bg=FF8C00&text=Image%203" alt="">
                    </a>
                    <div class="carousel-caption">
                        <h3>Proactively Pontificate</h3>
                        <p>Holistically pontificate installed base portals after maintainable products.</p>
                    </div>
                </div>
            </div>
            <a class="left carousel-control" href="#side-carousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#side-carousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </div><!--/Right Column -->
</#macro>
<#macro leftPanel>
    <div class="col-sm-3">

        <!-- List-Group Panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title"><span class="glyphicon glyphicon-random"></span> Completely Synergize</h1>
            </div>
            <div class="list-group">
                <a href="#" class="list-group-item">Resource Taxing</a>
                <a href="#" class="list-group-item">Premier Niche Markets</a>
                <a href="#" class="list-group-item">Dynamically Innovate</a>
                <a href="#" class="list-group-item">Objectively Innovate</a>
                <a href="#" class="list-group-item">Proactively Envisioned</a>
            </div>
        </div>

        <!-- Text Panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h1 class="panel-title"><span class="glyphicon glyphicon-cog"></span> Dramatically Engage</h1>
            </div>

            <div class="panel-body">
                <p>Objectively innovate empowered manufactured products whereas parallel platforms. Holisticly
                    predominate extensible testing procedures for reliable supply chains. Dramatically engage top-line
                    web services vis-a-vis cutting-edge deliverables.</p>
                <p>
                    <button class="btn btn-default">Engage</button>
                </p>
            </div>
        </div>

        <!-- Text Panel -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">
                    <span class="glyphicon glyphicon-bullhorn"></span>
                    Active Predomination
                </h3>
            </div>
            <div class="panel-body">
                <p>Proactively envisioned multimedia based expertise and cross-media growth strategies.</p>
                <div class="btn-group" role="group">
                    <button type="button" class="btn btn-default">Resource</button>
                    <button type="button" class="btn btn-default">Envision</button>
                    <button type="button" class="btn btn-default">Niche</button>
                </div>
            </div>
        </div>

    </div><!--/Left Column-->

</#macro>
<#macro specialPage>
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width,initial-scale=0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <title>Blog</title>
        <link href="/css/style.css" rel="stylesheet">
        <!-- Custom CSS: You can use this stylesheet to override any Bootstrap styles and/or apply your own styles -->
        <link href="/css/custom.css" rel="stylesheet">
    </head>

    <body>
    <@navigation/>
    <#nested>
    <@footer/>
    </body>
    </html>
</#macro>
<#macro error field>
    <@spring.bind path=field/>
    <#if spring.status.errorMessage??>
        <div class="error">
            ${spring.status.errorMessage}
        </div>
    </#if>
</#macro>
<#macro forgotPassword>
    <div class="forgotPassword">
        <a href="/forgotPassword">Forgot your password?</a>
    </div>
</#macro>
<#macro formHeader>
    <div class="panel panel-default">
        <div class="panel-heading">
            <h3 class="panel-title">
                <span class="glyphicon glyphicon-log-in"></span>
                <#nested>
            </h3>
        </div>
    </div>
</#macro>
<#macro resetPassword>
    <@formHeader>Reset Password</@formHeader>
    <div class="panel-body">
        <form action="/forgotPassword" method="post">
            <div class="form-group">
                Email: <input type="text" class="form-control" name="email" value="">
            </div>
            <@error 'forgotPasswordForm.email'/>
            <button type="submit" class="btn btn-default">Reset</button>
        </form>
    </div>
</#macro>
<#macro showPasswordForm>
    <@formHeader>Make up new password</@formHeader>
    <div class="panel-body">
        <form action="/resetPassword" method="post">
            <div class="form-group">
                Password: <input type="password" class="form-control" name="password" value="">
            </div>
            <@error 'resetPasswordForm.password'/>
            <div class="form-group">
                Password Confirmation: <input type="password" class="form-control" name="passwordConfirmation" value="">
            </div>
            <@error 'resetPasswordForm.passwordConfirmation'/>

            <@error 'resetPasswordForm'/>
            <input type="hidden" name="token" value="${token.token}">
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </div>
</#macro>
<#macro register>
    <!-- Form -->
    <@formHeader>Register</@formHeader>
    <div class="panel-body">
        <form action="/register" method="post">
            <div class="form-group">
                Name: <input type="text" class="form-control" name="name" value="${registerForm.name!}">
            </div>
            <@error 'registerForm.name'/>
            <div class="form-group">
                Surname: <input type="text" class="form-control" name="surname" value="${registerForm.surname!}">
            </div>
            <@error 'registerForm.surname'/>
            <div class="form-group">
                Login: <input type="text" class="form-control" id="login" name="login" value="${registerForm.login!}">
            </div>
            <@error 'registerForm.login'/>
            <div class="form-group">
                Email: <input type="text" class="form-control" id="email" name="email" value="${registerForm.email!}">
            </div>
            <@error 'registerForm.email'/>
            <div class="form-group">
                Password: <input type="password" class="form-control" id="password" name="password">
            </div>
            <@error 'registerForm.password'/>
            <div class="form-group">
                Password confirmation: <input type="password" class="form-control" name="passwordConfirmation">
            </div>
            <@error 'registerForm'/>
            <button type="submit" class="btn btn-default">Register</button>
        </form>
    </div>
</#macro>
<#macro login>
    <!-- Form -->
    <@formHeader>Log in</@formHeader>
    <div class="panel-body">
        <form action="/login" method="post">
            <div class="form-group">
                Login or email: <input type="text" class="form-control" id="uid" name="loginOrEmail"${loginForm!}>
            </div>
            <div class="form-group">Password:<input type="password" class="form-control" id="pwd" name="password">
            </div>
            <@error 'loginForm.password'/>
            <button type="submit" class="btn btn-default">Log In</button>
            <@forgotPassword/>
        </form>
    </div>
</#macro>
