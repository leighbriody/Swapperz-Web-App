<%@page import="business.User"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Home </title>

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
                        <a class="blog-header-logo text-dark shopName" href="#">Swapperz</a>
                    </div>

                </div>
            </header>
            <!--FOR LOGIN TESTING TO SEE IF A USER IS LOGGED IN-->
            <% //Check if user logged in is not equal to null
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (loggedInUser != null) {

                    //Now we need to see are admin user or regular user
                    boolean isAdminUser = false;
                    if (loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                        isAdminUser = true;
                    }

            %>


            <!--NOW IF THE USER IS AN ADMIN OR NOT WE NEED TO DISPLAY DIFFERNT NAVBARS -->
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted " href="sellProduct.jsp">SELL</a>
                    <%                        if (!loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                    %>
                    <a class="p-2 text-muted " href="upgradeToPro.jsp">GO PRO</a>
                    <%
                        }
                    %>
                    <a class="p-2 text-muted " href="cart.jsp">CART</a>
                    <%                            if (isAdminUser) {
                    %>
                    <!--HAVE A DROPDOWN ADMIN LIST -->
                    <div class="dropdown">
                        <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                            ADMIN
                        </button>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="approvePurchases.jsp">PENDING PURCHASES</a>  
                            <a class="dropdown-item" href="addBrand.jsp">ADD BRAND</a>
                            <a class="dropdown-item disabled" href="#">USERS[In Development]</a>
                        </div>
                    </div>


                    <%}%>
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>

            <!--JUMBOTRON GRIDS -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3 ">Welcome To Swapperz, <%=loggedInUser.getUsername()%></h1>
                    <p class="lead my-3">The leading application in buying and selling clothes</p>
                    <!--------------------------------------------------->
                </div>
                <div class="col-md-6 ">
                    <img class="blackHoodie" src="images/blackHoodieNoBg.png">

                </div>
            </div>
            <%} else {
            %> 
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted" href="register.jsp">REGISTER</a>


                </nav>
            </div>
            <!--make this link to the products they can buy or recommended-->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3 ">Welcome To Swapperz</h1>
                    <p class="lead my-3">The leading application in buying and selling clothes</p>
                    <p class="lead mb-0"><a href="register.jsp" class="text-white font-weight-bold"><button class="btn btn-primary">Get Me Started</button></a></p>
                    <!--------------------------------------------------->
                </div>
                <div class="col-md-6 ">
                    <img class="blackHoodie" src="images/blackHoodieNoBg.png">

                </div>
            </div>
            <%}%>

            <!--MORE -->
            <!--JUMBOTRON GRIDS -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">


                    <h3 class="mb-0">
                        <a class="text-dark" href="#">What Is Swapperz ?</a>
                    </h3>
                    <p class="card-text mb-auto">Swapperz allows users to sign in to buy and sell clothes.</p>
                    <a href="#">Continue reading</a>
                </div>


                <div class="col-md-6 ">
                    <img class="retroShoe" src="images/retroShoe.png">
                </div>
            </div>






            <footer class="blog-footer">

                <p>
                    <a href="#">Back to top</a>
                    <br>

                </p>
            </footer>
        </div>


        <!-- Bootstrap core JavaScript
        ================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script>window.jQuery || document.write('<script src="../../../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
        <script src="../../../../assets/js/vendor/popper.min.js"></script>
        <script src="../../../../dist/js/bootstrap.min.js"></script>
        <script src="../../../../assets/js/vendor/holder.min.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
        <script>
            Holder.addTheme('thumb', {
                bg: '#55595c',
                fg: '#eceeef',
                text: 'Thumbnail'
            });
        </script>
    </body>
</html>
