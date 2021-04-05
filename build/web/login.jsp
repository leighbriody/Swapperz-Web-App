<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Login </title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="https://fonts.googleapis.com/css?family=Playfair+Display:700,900" rel="stylesheet">
        <link href="blog.css" rel="stylesheet">
        <link href="style.css" rel="stylesheet">
    </head>

    <body>

        <div class="container">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">

                    <div class="col-12 text-center">
                        <a class="blog-header-logo text-dark" href="#">Swapperz</a>
                    </div>

                </div>
            </header>

            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted active" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted" href="register.jsp">REGISTER</a>

                </nav>
            </div>



            <main role="main">
                <form class="form-signin text-center" action="FactoryServlet" method="post">
                    <!--hidden input to identify what user wants to do-->
                    <input type="hidden" name ="action" value="login" />
                    <img class="mb-4" src="images/loginGuy.jpg" alt="" width="50%" height="50%" >
                    <h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
                    <!--for an error message (aesthetically better)-->
                    <%
                        // Get the error message variable out of the session
                        Object msg = session.getAttribute("errorMessage");
                        // Cast it to a String so we can use it
                        String error = (String) msg;
                        if (error != null) {
                            // Display the message
%>

                    <div class="text-danger pb-4"> <%=error%> </div>
                    <%
                        }
                        // We have finished with the results of this action
                        // so now we can remove the value from the session
                        session.removeAttribute("errorMessage");
                        // This makes sure that old error messages 
                        // don't mistakenly get printed out later
%> 
                    <!--username-->
                   <label  class="float-left">Username :</label>
                    <label for="inputUsername" class="sr-only">Username</label>
                    <input type="text" id="inputUsername" class="form-control"   autofocus name="username">
                    <!--password-->
                     <label  class="float-left">Password :</label>
                    <label for="inputPassword" class="sr-only">Password</label>
                    <input type="password" id="inputPassword" class="form-control" " name="password">
                    <div class="checkbox mb-3">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" value="login">Sign in</button>
                    </div>
                    <p>Forgot Password?<a href="recoverPassword.jsp"> Click Here</a></p>
                </form>
            </main>


            <footer class="blog-footer">
            
                <p>
                    <a href="#">Back to top</a>
                </p>
            </footer>


            <!-- Bootstrap core JavaScript
            ================================================== -->
            <!-- Placed at the end of the document so the pages load faster -->
            <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
            <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
            <script src="../../../../assets/js/vendor/popper.min.js"></script>
            <script src="../../../../dist/js/bootstrap.min.js"></script>
            <script src="../../../../assets/js/vendor/holder.min.js"></script>
            <script>
                Holder.addTheme('thumb', {
                    bg: '#55595c',
                    fg: '#eceeef',
                    text: 'Thumbnail'
                });
            </script>
    </body>
</html>