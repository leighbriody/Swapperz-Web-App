<%@page import="business.User"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Men </title>

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
                
                
                 //Now we need to see are admin user or regular user
                boolean isAdminUser = false;
                if (loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                    isAdminUser = true;
                }
                
                if (loggedInUser != null) {
            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted " href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted " href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted active" href="upgradeToPro.jsp">GO PRO</a>
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

            <%} else {
            %> 
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted active" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted" href="register.jsp">REGISTER</a>


                </nav>
            </div>

            <%}%>



            <!--UPGRADE TO PRO SECTION -->
            <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
                <h1 class="display-4">Let's Go Pro !</h1>
                <p class="lead">Upgrade to pro to gain access to the pro section and eliminate your transaction fees !</p>
            </div>

           
                <div class="card-deck mb-3 text-center">
                    <div class="card mb-4 box-shadow">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">+ 1 Month</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">$6<small class="text-muted">/ mo</small></h1>
                            <ul class="list-unstyled mt-3 mb-4">
                                <li>Access To The Pro Section</li>
                                <li>No More $1 Transaction Fees !</li>
                                
                            </ul>
                           <button type="button" class="btn btn-lg btn-block btn-primary">+ 1 Month For$6</button>
                        </div>
                    </div>
                    <div class="card mb-4 box-shadow">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">+6 Month's</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">$3 <small class="text-muted">/ mo</small></h1>
                            <ul class="list-unstyled mt-3 mb-4">
                                <li>Access To The Pro Section</li>
                                <li>No More $1 Transaction Fees !</li>
                            </ul>
                            <button type="button" class="btn btn-lg btn-block btn-primary">+ 6 Months For $18</button>
                        </div>
                    </div>
                    <div class="card mb-4 box-shadow">
                        <div class="card-header">
                            <h4 class="my-0 font-weight-normal">+12 Month's</h4>
                        </div>
                        <div class="card-body">
                            <h1 class="card-title pricing-card-title">$2.5<small class="text-muted">/ mo</small></h1>
                            <ul class="list-unstyled mt-3 mb-4">
                               <li>Access To The Pro Section</li>
                                <li>No More $1 Transaction Fees !</li>
                            </ul>
                            <button type="button" class="btn btn-lg btn-block btn-primary">+ 12 Months For $30</button>
                        </div>
                    </div>
                </div>
















                <footer class="blog-footer">

                    <p>
                        <a href="#">Back to top</a>
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
                   <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
            <script>
                Holder.addTheme('thumb', {
                    bg: '#55595c',
                    fg: '#eceeef',
                    text: 'Thumbnail'
                });
            </script>
    </body>
</html>