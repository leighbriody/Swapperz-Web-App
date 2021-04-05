<%-- 
    Document   : search
    Created on : 22-Feb-2021, 21:17:19
    Author     : Osama Kheireddine
--%>
<%@page import="daos.ProductImageDao"%>
<%@page import="business.Product"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.Colour"%>
<%@page import="business.Brand"%>
<%@page import="business.Size"%>
<%@page import="daos.ColourDao"%>
<%@page import="daos.SizeDao"%>
<%@page import="daos.BrandDao"%>
<%@page import="business.User"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Search</title>

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
            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
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


                </nav>
            </div>
            <%}%>
            <section>
                <img src="images/searchPageBanner.jpg" class="d-block w-100" alt="...">
                <div class="searchBannerText">Search for a Style...</div>
                <div class="searchBannerSubText">Select terms from below to compose a unique search</div>
            </section>
            <section class="search-sec">
                <%
                    //for this form we need access to get all items required for a search
                    BrandDao bDao = new BrandDao("swapperz");
                    SizeDao sDao = new SizeDao("swapperz");
                    ColourDao cDao = new ColourDao("swapperz");
                %>
                <form action="FactoryServlet" method="post">
                    <input type="hidden" name="action" value="search">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="row">
                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <select name="colour"  class="form-control search-slt" id="form-control search-slt">
                                        <option selected>Colour</option>
                                        <%
                                            for (Colour c : cDao.getAllColours()) {
                                        %>
                                        <option value="<%= c.getId()%>"><%= c.getColour()%></option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <select name="brand" class="form-control search-slt">
                                        <option selected>Brand</option>
                                        <%
                                            for (Brand b : bDao.getAllBrands()) {
                                        %>
                                        <option value="<%= b.getId()%>"><%= b.getName()%></option>
                                        <%
                                            }
                                        %>                                        
                                    </select>
                                </div>
                                <!--NEED TO HAVE A PLACE FOR PRICE HERE-->
                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <select name="condition" class="form-control search-slt">
                                        <option selected>Condition</option>
                                        <option value="worn">Worn</option>
                                        <option value="likenew">Like New</option>
                                        <option value="brandnew">Brand New</option>
                                    </select>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <select name="gender" class="form-control search-slt" id="form-control search-slt">
                                        <option selected>Gender</option>
                                        <option value="male">Male</option>
                                        <option value="female">Female</option>
                                        <option value="unisex">Unisex</option>
                                    </select>
                                </div>
                                <div class="col-lg-1 col-md-1 col-sm-12 p-0">
                                    <select name="category" class="form-control search-slt" id="form-control search-slt">
                                        <option selected>Category</option>
                                        <option value="menshoodies">Mens Hoodies</option>
                                        <option value="mensjeans">Mens Jeans</option>
                                        <option value="womenstrainers">Womens Trainers</option>
                                         <option value="menstrainers">Womens Trainers</option>
                                    </select>
                                </div>
                                <div class="col-lg-3 col-md-3 col-sm-12 p-0">
                                    <input type="submit" class="btn btn-danger wrn-btn" value="Search" >
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
                <%
                    // Get the error message variable out of the session
                    Object msg = session.getAttribute("errorMessage");
                    // Cast it to a String so we can use it
                    String error = (String) msg;
                    if (error != null) {
                        // Display the message
%>
                <div class="text-danger mt-5 text-center"> <h3><%= error.toUpperCase()%></h3> </div>
                <%
                    }
                    session.removeAttribute("errorMessage");
                %> 
            </section>
            <!--DISPLAYING THE RESULTS-->
            <%
                //get the session attribute for the searched results
                ArrayList<Product> searchResults = new ArrayList();
                searchResults = (ArrayList<Product>) session.getAttribute("searchResults");
              
                ProductImageDao prodImageDao = new ProductImageDao("swapperz");
            %>
            <!--PLACES SEARCHES DOWN HERE-->
            <div class="container">
                <div class="row">
                    <% if (searchResults.size() == 0) {%>
                    <h2 class="text-danger">No results</h2>
                    <%
                    } else {
                        for (Product p : searchResults) {
                            int imageId = p.getProductImage();

                            String imageLocation = prodImageDao.getProdcutImageLocation(imageId);
                            String img = imageLocation.replace("\\", "/");
                    %>
                    <div class="col-md-4 mt-2">
                        <div class="card">
                            <div class="card-body">

                                <div class="card-img-actions"> <img src="<%=img%>" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                            </div>
                            <div class="card-body bg-light text-center">
                                <div class="mb-2">
                                    <h6 class="font-weight-semibold mb-2"> <a href="#" class="text-default mb-2" data-abc="true"><%=p.getProductName()%>  </a> </h6>
                                </div>
                                <h3 class="mb-0 font-weight-semibold">$<%=p.getOriginalPrice()%></h3>
                                <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                                <div class="text-muted mb-3">Seller Id : <%=p.getUserId()%></div> <button type="button" class="btn bg-cart"><i class="fa fa-cart-plus mr-2"></i>Show Me More</button>
                                <p><%=img%></p>

                            </div>
                        </div>
                    </div>
                    <%        }
                        }
                    %>
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
        <script>
            Holder.addTheme('thumb', {
                bg: '#55595c',
                fg: '#eceeef',
                text: 'Thumbnail'
            });
        </script>
    </body>
</html>
