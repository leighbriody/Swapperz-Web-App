<%-- 
    Document   : editAd
    Created on : 04-Mar-2021, 17:58:51
    Author     : ME
--%>
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
                    //user is logged in
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

                    int productId = Integer.parseInt(request.getParameter("selectedAd"));

                    Product product = pDao.getProductById(productId);
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

            <!--BANNER -->

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

                                    <div class="profile mr-3"><img src="<%=seller.getProfilePicture()%> " alt="..." width="130" class="rounded mb-2 img-thumbnail"><a href="editProfile.jsp" class="btn btn-outline-light btn-sm btn-block">Edit Profile</a></div>
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

                                        <p class="price-detail-wrap"> 
                                            <span class="price h3 text-success"> 
                                                <%
                                                    if (product.getListedPrice() == 0) {
                                                %>
                                                <span class="currency"> $</span><span class="num"><%=product.getOriginalPrice()%></span>
                                                <%
                                                } else {
                                                %>
                                                <span class="currency text-muted"> Was:($</span><span class="num text-muted"><%=product.getOriginalPrice()%>)</span>
                                                <br>
                                                <span class="currency">NOW: $</span><span class="num"><%=product.getListedPrice()%></span>
                                                <%
                                                    }
                                                %>
                                            </span> 

                                        </p> <!-- price-detail-wrap .// -->
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

                                        <!--form to update the price-->
                                        <form action='FactoryServlet' method='post'>
                                            <label class='font-weight-bold'>NEW PRICE: <i>$</i></label>
                                            <input type='number' name='newPrice' min='1' max='<%= product.getOriginalPrice() * 2%>' value='<%= product.getOriginalPrice()%>'>
                                            <input class="btn btn-lg btn-success text-uppercase" type="submit" value='CHANGE PRICE'>
                                            <input type='hidden' name='userId' value='<%= product.getUserId()%>'>
                                            <input type='hidden' name='adId' value='<%= productId%>'>
                                            <input type='hidden' name='action' value="editAdPrice">
                                        </form>

                                    </article> <!-- card-body.// -->
                                </aside> <!-- col.// -->
                            </div> <!-- row.// -->
                        </div> <!-- card.// -->



                    </div>
                </div>
                <%} else {
                %> 
                <div class="nav-scroller py-1 mb-2">
                    <nav class="nav d-flex justify-content-between">
                        <a class="p-2 text-muted " href="index.jsp">HOME</a>
                        <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                        <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                        <a class="p-2 text-muted" href="login.jsp">Login</a>
                        <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                    </nav>
                </div>  
                <h1>Uh oh... It looks like you've been logged out:(</h1>
                <p class='text-muted'>psst... you can <u><a class="text-muted" href="login.jsp">login here</a></u></p>
                <img class="float-right" src="https://media1.tenor.com/images/9413ffc5a11722a3cc456a88810750bd/tenor.gif?itemid=14193216">

                <%}%>
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

