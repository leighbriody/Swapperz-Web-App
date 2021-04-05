<%@page import="daos.UserDao"%>
<%@page import="business.User"%>
<%@page import="daos.ProductImageDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Product"%>
<%@page import="business.Product"%>
<%@page import="daos.ProductDao"%>
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

        <%
            //data bundl here for when we internationalise
            UserDao uDao = new UserDao("swapperz");

            String catgeoryChosen = request.getParameter("categoryChosen");

            ProductDao pDao = new ProductDao("swapperz");
            ProductImageDao prodImageDao = new ProductImageDao("swapperz");
            ArrayList<Product> productsOfCategory = pDao.allProductsOfCategoryChosen(catgeoryChosen);

            //We need to get the cart and store it so that if the user has something already in their cart they cannot add it to cart again
            ArrayList<Product> cart = new ArrayList<Product>();

            if (session.getAttribute("cart") != null) {
                cart = (ArrayList<Product>) session.getAttribute("cart");
            }
        %>

        <div class="container">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">

                    <div class="col-12 text-center">
                        <a class="blog-header-logo text-dark shopName" href="#">Swapperz  </a>
                    </div>

                </div>
            </header>



            <!--FOR LOGIN TESTING TO SEE IF A USER IS LOGGED IN-->
            <% //Check if user logged in is not equal to null
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                if (loggedInUser != null) {
            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <%
                        if (catgeoryChosen.substring(0, 3).equalsIgnoreCase("men")) {
                    %>
                    <a class="p-2 text-muted active" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <%
                        }
                        if (catgeoryChosen.substring(0, 5).equalsIgnoreCase("women")) {
                    %>
                    <a class="p-2 text-muted " href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted active" href="pickWomensCategory.jsp">WOMEN</a>
                    <%
                        }
                    %>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>


            <%} else {
            %> 
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted" href="index.jsp">HOME</a>
                    <%
                        if (catgeoryChosen.substring(0, 3).equalsIgnoreCase("men")) {
                    %>
                    <a class="p-2 text-muted active" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <%
                        }
                        if (catgeoryChosen.substring(0, 5).equalsIgnoreCase("women")) {
                    %>
                    <a class="p-2 text-muted " href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted active" href="pickWomensCategory.jsp">WOMEN</a>
                    <%
                        }
                    %>
                    <a class="p-2 text-muted" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted" href="register.jsp">REGISTER</a>

                </nav>
            </div>

            <%}%>

            <!--BANNER -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3"><%=catgeoryChosen%></h1>



                    <!--------------------------------------------------->
                </div>

            </div>



            <!--ITEMMS FOR SALE  --> 
            <!--THESE WILL BE LOOPED TROUGH THE RESULT -->
            <!--MUST SHOW SELLER NAME  , PRODUCT NAME AND PRICE -->

            <div class="album py-5 bg-light">
                <div class="container">
                    <div class="row">
                        <%
                            if (productsOfCategory.size() == 0) {
                                //html to show sorry there are currently no products of this catego


                        %> 
                        <h1>No Products Of <%=catgeoryChosen%></h1>
                        <%
                        } else {
                            //we must loop trough product 

                            //-!!NEED TO ADD IMAGE
                            //what product details do we need to display ??
                            //productName
                            //original price
                            //seller name - username
                            for (Product p : productsOfCategory) {

                                //to see if its at original price or not
                                double productPrice = p.getListedPrice();
                                if (productPrice == 0) {
                                    productPrice = p.getOriginalPrice();
                                }

                                //Little flag to check if the item is already in their cart
                                boolean inCartAlready = false;
                                if (cart.contains(p)) {
                                    inCartAlready = true;
                                }

                                String productName = p.getProductName();
                                double price = p.getOriginalPrice();

                                //User object of the seller
                                User seller = uDao.getUserById(p.getUserId());
                                int imageId = p.getProductImage();

                                String imageLocation = prodImageDao.getProdcutImageLocation(imageId);
                                String img = imageLocation.replace("\\", "/");
                                if (p.getStatus().equalsIgnoreCase("forsale")) {
                        %>
                        <div class="col-md-4 mt-2">
                            <div class="card">
                                <div class="card-body">

                                    <div class="card-img-actions"> <img src="<%=img%>" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                                </div>
                                <div class="card-body bg-light text-center">
                                    <div class="mb-2">
                                        <h6 class="font-weight-semibold mb-2"> <a href="productDetails.jsp?productId=<%=p.getId()%>&sellerId=<%=p.getUserId()%>" class="text-default mb-2" data-abc="true"><%=p.getProductName()%>  </a> </h6>
                                    </div>
                                    <%
                                        if (p.getListedPrice() == 0) {
                                    %>
                                    <h3 class="mb-0 font-weight-semibold">$<%=p.getOriginalPrice()%></h3>
                                    <%
                                    } else if (p.getListedPrice() > p.getOriginalPrice()) {
                                    %>
                                    <h3 class="mb-0 font-weight-semibold">$<%=p.getListedPrice()%></h3>
                                    <%
                                    } else {
                                    %>
                                    <h5 class="mb-0 font-weight-semibold text-danger">WAS: $<%=p.getOriginalPrice()%></h5>
                                    <h3 class="mb-0 font-weight-semibold text-success">NOW: $<%=p.getListedPrice()%></h3>
                                    <%
                                        }
                                    %>
                                    <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                                    <div class="text-muted mb-3"><a href="sellerProfile.jsp?sellerId=<%=seller.getId()%>"><p>Seller : <%=seller.getUsername()%></p></a></div> <a href="productDetails.jsp?productId=<%=p.getId()%>&sellerId=<%=p.getUserId()%>"><button type="button" class="btn bg-cart btn-success"><i class="fa fa-cart-plus mr-2"></i>Show Me More </button></a>
                                    <% if (inCartAlready) { %>
                                    <button type="button" class="btn bg-cart btn-success"><i class="fa fa-cart-plus mr-2"></i>In Cart Already !</button>


                                    <%} else {%>

                                    <a href="FactoryServlet?action=addToCart&productId=<%=p.getId()%>" class="btn btn-primary"> Add To Cart<span class="num">$<%= productPrice%></span> </a>

                                    <%}%>
                                </div>
                            </div>
                        </div>
                        <%
                                    }
                                }
                            }


                        %>
                    </div>
                </div>
                <br>
                <br>
                <br>
                <br>
                <br>
                <footer class="blog-footer">

                    <p>
                        <a href="#">Back to top</a>
                    </p>
                </footer>
            </div>

        </div>
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
