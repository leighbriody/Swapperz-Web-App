<%@page import="business.Address"%>
<%@page import="daos.AddressDao"%>
<%@page import="daos.UserDao"%>
<%@page import="daos.BrandDao"%>
<%@page import="business.Brand"%>
<%@page import="daos.SizeDao"%>
<%@page import="business.Size"%>
<%@page import="daos.ProductImageDao"%>
<%@page import="business.Product"%>
<%@page import="java.util.ArrayList"%>
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

        <title>Swapperz - Seller Profile </title>

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

                UserDao uDao = new UserDao("swapperz");
                int sellerId = Integer.parseInt(request.getParameter("sellerId"));

                User seller = uDao.getUserById(sellerId);

                //now we get the users address details and store in an address object
                AddressDao aDao = new AddressDao("swapperz");
                Address usersAddress = aDao.getUserAddress(seller.getId());

                String adds = "forSale";
                adds = request.getParameter("adds");
                
                //Create instance of the product dao
                ProductDao p = new ProductDao("swapperz");

                ArrayList<Product> productsOfStatus = p.allUsersProductsOfStatus(seller.getId(), adds);

                ProductImageDao prodImageDao = new ProductImageDao("swapperz");

                boolean showActiveAds = false;
                boolean showPreviousAds = false;

                if (loggedInUser != null) {
            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted active" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>



            <!-- MY PROFILE -->
            <div class="bg-dark shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">

                        <div class="profile mr-3"><img src="<%= seller.getProfilePicture()%>" alt="..." width="130" class="rounded mb-2 img-thumbnail"></div>
                        <div class="media-body mb-5 text-white">


                            <p class="small mb-2"> <i class="fas fa-map-marker-alt mr-2"></i><%=seller.getfName() + " , " + seller.getlName()%> </p>
                            <p class="small mb-2"> <i class="fas fa-map-marker-alt mr-2"></i><%=usersAddress.getCounty() + " , " + usersAddress.getCountry()%></p>
                            <p class="small mb-2"> <i class="fas fa-map-marker-alt mr-2"></i><%=seller.getEmail() + " , " + seller.getPhone()%></p>


                        </div>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <a href= "sellerProfile.jsp?adds=forSale&sellerId=<%=seller.getId()%>">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(seller.getId(), "forSale")%></h5><small class="text-muted"> <i class="fas fa-image mr-1"></i>Seller Active Ads</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "sellerProfile.jsp?adds=sold&sellerId=<%=seller.getId()%>">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(seller.getId(), "sold")%></h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>Seller Sold Ads</small></a>
                        </li>


                    </ul>
                </div>

            </div>

            <!-- BASED ON IF THE USER SELECTED ACTIVE ADDS OR PEVIOUS ADDS WE NEED TO KNOW WHAT TO DISPLAY -->
            <div class="row">
                <%
                    if (productsOfStatus.size() == 0) {
                        //html to show sorry there are currently no products of this catego


                %> 
                <h1>No Products  <%=adds%></h1>
                <%
                } else {
                    //we must loop trough product 

                    //-!!NEED TO ADD IMAGE
                    //what product details do we need to display ??
                    //productName
                    //original price
                    //seller name - username
                    for (Product pd : productsOfStatus) {

                        String productName = pd.getProductName();
                        double price = pd.getOriginalPrice();

                        int imageId = pd.getProductImage();

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
                                <h6 class="font-weight-semibold mb-2"> <a href="#" class="text-default mb-2" data-abc="true"><%=pd.getProductName()%>   </a> </h6>
                            </div>
                            <h3 class="mb-0 font-weight-semibold">$<%=pd.getOriginalPrice()%></h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>


                            <!--IF WE ARE ON THE ACTIVE ADDS THIS BUTTON WILL BE A WITHDRAW BUTTON -->
                            <%
                                if (adds.equalsIgnoreCase("forSale")) {


                            %>
                            <a href='productDetails.jsp?productId=<%=pd.getId()%>&sellerId=<%=pd.getUserId()%>'> <button type="button" class="btn btn-success">Show Me More</button></a>

                            <%}%>

                            <!--IF WE ARE ON THE WITHDRAWN ADDS THE BUTTON WILL BE TO RE LIST ADD , AND A BUTTON TO REMOVE ADD COMPLLETLET -->
                            <%
                                if (adds.equalsIgnoreCase("withdrawn")) {


                            %>
                            <a href='FactoryServlet?action=relistAdd&id=<%=pd.getId()%>'> <button type="button" class="btn btn-success text-center">Re-List</button></a>
                            <a href='FactoryServlet?action=removeAdd&id=<%=pd.getId()%>'> <button type="button" class="btn btn-danger"></i>Remove</button></a>

                            <%}%>

                        </div>
                    </div>
                </div>
                <%
                        }
                    }

                %>
            </div>



            <!--ELSE THE USER IS NOT LOGGED OR THEIR SESSION HAS TIMED OUT -->
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
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3 ">Welcome To Your Profile</h1>
                    <p class="lead my-3">Some Text</p>
                    <p class="lead mb-0"><a href="register.jsp" class="text-white font-weight-bold"><button class="btn btn-primary">Get Me Started</button></a></p>
                    <!--------------------------------------------------->
                </div>
                <div class="col-md-6 ">
                    <img class="blackHoodie" src="images/blackHoodieNoBg.png">

                </div>
            </div>
            <%}%>








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
