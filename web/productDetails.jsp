<%@page import="daos.AddressDao"%>
<%@page import="daos.SizeDao"%>
<%@page import="daos.SizeDao"%>
<%@page import="daos.SizeDao"%>
<%@page import="daos.BrandDao"%>
<%@page import="daos.ColourDao"%>
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

            //seller info 
            int sellerId = Integer.parseInt(request.getParameter("sellerId"));

            User seller = uDao.getUserById(sellerId);

            ProductDao pDao = new ProductDao("swapperz");
            ProductImageDao prodImageDao = new ProductImageDao("swapperz");

            AddressDao aDao = new AddressDao("swapperz");

            ColourDao cDao = new ColourDao("swapperz");

            BrandDao bDao = new BrandDao("swapperz");

            SizeDao sDao = new SizeDao("swapperz");

            int productId = Integer.parseInt(request.getParameter("productId"));

            Product product = pDao.getProductById(productId);

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
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
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
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>

                </nav>
            </div>

            <%}%>

            <!--BANNER -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">



                    <!--------------------------------------------------->
                </div>

            </div>



            <!--ITEMMS FOR SALE  --> 
            <!--THESE WILL BE LOOPED TROUGH THE RESULT -->
            <!--MUST SHOW SELLER NAME  , PRODUCT NAME AND PRICE -->

            <div class="album py-5 bg-white">
                <div class="container">
                    <div class="container">

                        <!-- USER PROFILE INFO -->
                        <div class="bg-dark shadow rounded overflow-hidden">
                            <div class="px-4 pt-0 pb-4 cover">
                                <div class="media align-items-end profile-head">

                                    <div class="profile mr-3"><img src="<%=seller.getProfilePicture()%> " alt="..." width="130" class="rounded mb-2 img-thumbnail"><a href="sellerProfile.jsp?sellerId=<%=product.getUserId()%>" class="btn btn-outline-light btn-sm btn-block">View Profile</a></div>
                                    <div class="media-body mb-5 text-white">

                                        <h4 class="mt-0 mb-0"></h4>
                                        <p class="small mb-4"> <i class="fas fa-map-marker-alt mr-2"></i><%=seller.getfName() + "," + seller.getlName()%> </p>
                                        <p class="small mb-4"> <i class="fas fa-map-marker-alt mr-2"></i><%=aDao.getUserAddress(seller.getId()).getCounty() + " , " + aDao.getUserAddress(seller.getId()).getCountry()%> </p>
                                    </div>
                                </div>
                            </div>


                        </div>


                        <div class="card">


                            <div class="row">
                                <aside class="col-sm-5 border-right">
                                    <article class="gallery-wrap"> 
                                        <div class="img-big-wrap">
                                            <div> <a href="#"><img src="<%=prodImageDao.getProdcutImageLocation(product.getProductImage())%>"></a></div>
                                        </div> <!-- slider-product.// -->

                                    </article> <!-- gallery-wrap .end// -->
                                </aside>

                                <aside class="col-sm-7">
                                    <article class="card-body p-5">
                                        <hr>
                                        <h3 class="title mb-3"><%=product.getProductName()%></h3>
                                        <%
                                            if (product.getListedPrice() == 0) {
                                        %>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-success"> 
                                                <span class="currency"> $</span><span class="num"><%=product.getOriginalPrice()%></span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <%
                                        } else {
                                        %>
                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-success"> 
                                                <span class="currency"> $</span><span class="num"><%=product.getListedPrice()%></span>
                                            </span> 
                                        </p> <!-- price-detail-wrap .// -->
                                        <%
                                            }
                                        %>
                                        <dl class="item-property">
                                            <dt>Description</dt>
                                            <dd><p><%=product.getDescription()%></p></dd>
                                        </dl>
                                        <dl class="param param-feature">
                                            <dt>Colour</dt>
                                            <dd><%=cDao.getColour(product.getColour()).getColour()%></dd>
                                        </dl>  <!-- item-property-hor .// -->
                                        <dl class="param param-feature">
                                            <dt>Brand</dt>
                                            <dd><%=bDao.getBrand(product.getBrand()).getName()%></dd>
                                        </dl>  <!-- item-property-hor .// -->


                                        <dl class="param param-feature">
                                            <dt>Condition</dt>
                                            <dd><%=product.getProductCondition()%></dd>
                                        </dl> 

                                        <dl class="param param-feature">
                                            <dt>Size </dt>
                                            <dd><%=sDao.getSize(product.getSize()).getSize()%></dd>
                                        </dl> 

                                        <hr>

                                        <%
                                            if (loggedInUser != null) {

                                                double productPrice = product.getListedPrice();
                                                if (productPrice == 0) {
                                                    productPrice = product.getOriginalPrice();
                                                }

                                        %>
                                        <a href="FactoryServlet?action=buyNow&productId=<%=product.getId()%>" class="btn btn-lg btn-success text-uppercase"> Buy now <span class="num">$<%= productPrice%></span> </a>
                                        <a href="FactoryServlet?action=addToCart&productId=<%=product.getId()%>" class="btn btn-lg btn-primary text-uppercase"> Add To Cart<span class="num">$<%= productPrice%></span> </a>


                                        <%
                                        } else {
                                        %>
                                        <a href="login.jsp" class="btn btn-lg btn-success text-uppercase"> Login to Buy</a>
                                        <%
                                            }
                                        %>
                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->



                    </div>
                </div>

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
