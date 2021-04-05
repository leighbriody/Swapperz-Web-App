<%@page import="daos.ProductDao"%>
<%@page import="business.User"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Women </title>

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

                ProductDao pDao = new ProductDao("swapperz");

                //Now we need to see are admin user or regular user
                if (loggedInUser != null) {
                    boolean isAdminUser = false;
                    if (loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                        isAdminUser = true;
                    }

            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted " href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted active" href="pickWomensCategory.jsp">WOMEN</a>
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


            <%} else {
            %> 
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted active" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted" href="register.jsp">REGISTER</a>
                </nav>
            </div>

            <%}%>

            <!--BANNER -->
            <div class="row mb-2 jumbotron-grid text-center ">
                <div class="col-md-12">

                    <h1 class="display-3 ">Womens Category</h1>
                </div>
                <div class="col-md-6 ">

                </div>
            </div>

            <div class="row">

                <!--RUNNERS -->
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensRunners.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">RUNNERS</h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens trainers")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens trainers"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
                    </div>
                </div>

                <!---HOODIES -->
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensHoodie_1.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">HOODIES</h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens hoodies")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens hoodies"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
                    </div>
                </div>



                <!--JEANS -->
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensJeans.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">JEANS</h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens jeans")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens jeans"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
                    </div>
                </div>


                <!--JACKETS -->
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensJackets.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">JACKETS   </h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens jackets")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens jackets"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
                    </div>
                </div>




                <!--OUTWEAR -->
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensLeggings.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">LEGGINGS</h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens leggings")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens leggings"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
                    </div>
                </div>

                <!--ACCESSORIES -->

                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">
                            <div class="card-img-actions"> <img src="images/womensAccessories.jpg" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">

                            <h3 class="mb-0 font-weight-semibold">ACCESSORIES</h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <div class="text-muted mb-3"><%=pDao.getNumberOfProductsOfCategoryForSale("womens accessories")%> Items</div> <a href="productsOfCategory.jsp?categoryChosen=womens accessories"<button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>VIEW  </button></a>
                        </div>
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
