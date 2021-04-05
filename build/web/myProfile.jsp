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

        <title>Swapperz - My Profile </title>

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
                    //get the addressId of the logged in user
                    UserDao uDao = new UserDao("swapperz");
                    int addressId = uDao.getUserAddressId(loggedInUser.getId());

                    //now we get the users address details and store in an address object
                    AddressDao aDao = new AddressDao("swapperz");
                    Address usersAddress = aDao.getUserAddress(loggedInUser.getId());

                    String adds = "forSale";
                    adds = request.getParameter("adds");
                    //Create instance of the product dao
                    ProductDao p = new ProductDao("swapperz");

                    ArrayList<Product> productsOfStatus = p.allUsersProductsOfStatus(loggedInUser.getId(), adds);

                    ProductImageDao prodImageDao = new ProductImageDao("swapperz");

                    boolean showActiveAds = false;
                    boolean showPreviousAds = false;

            %>
           <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted " href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted " href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted " href="upgradeToPro.jsp">GO PRO</a>
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
                    <a class="p-2 text-muted active" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>



            <!-- MY PROFILE -->
            <div class="bg-dark shadow rounded overflow-hidden">
                <div class="px-4 pt-0 pb-4 cover">
                    <div class="media align-items-end profile-head">

                        <div class="profile mr-3"><img src="<%= loggedInUser.getProfilePicture()%> " alt="..." width="130" class="rounded mb-2 img-thumbnail"><a href="editProfile.jsp" class="btn btn-outline-light btn-sm btn-block">Edit profile</a></div>
                        <div class="media-body mb-5 text-white">

                            <h4 class="mt-0 mb-0"></h4>
                            <p class="small mb-4"> <i class="fas fa-user mr-2"></i><%=loggedInUser.getfName().toUpperCase() + " " + loggedInUser.getlName().toUpperCase()%> </p>
                            <p class="small mb-4"> <i class="fas fa-map-marker-alt mr-2"></i><%=usersAddress.getCounty() + "," + usersAddress.getCountry()%></p>
                        </div>
                        <%//SUCCESS MESSAGE & ERROR MESSAGES HERE

                            //ERROR
                            // Get the error message variable out of the session
                            Object msg = session.getAttribute("errorMessage");
                            // Cast it to a String so we can use it
                            String error = (String) msg;
                            if (error != null) {
                                // Display the message
%>

                        <div class="text-danger"><h3> <%=error%> </h3></div>
                        <%
                            }
                            // We have finished with the results of this action
                            // so now we can remove the value from the session
                            session.removeAttribute("errorMessage");
                        %>
                        <%//SUCCESS MESSAGE & ERROR MESSAGES HERE

                            //ERROR
                            // Get the error message variable out of the session
                            Object successMsg = session.getAttribute("successMessage");
                            // Cast it to a String so we can use it
                            String success = (String) successMsg;
                            if (success != null) {
                                // Display the message
%>

                        <div class="text-success"><h3> <%=success%> </h3></div>
                        <%
                            }
                            // We have finished with the results of this action
                            // so now we can remove the value from the session
                            session.removeAttribute("successMessage");
                        %>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=forSale">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "forSale")%></h5><small class="text-muted"> <i class="fas fa-image mr-1"></i>My Active Ads</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=sold">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "sold")%></h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>My Sold Ads</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=withdrawn">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "withdrawn")%></h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>My Withdrawn Adds</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=listItem">  <h5 class="font-weight-bold mb-0 d-block">+</h5><small class="text-muted"> <i class="fas fa-user mr-1"></i>LIST ITEM</small></a>
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
                <br>
                <div class="col-md-4 mt-2">
                    <div class="card">
                        <div class="card-body">

                            <div class="card-img-actions"> <img src="<%=img%>" class="card-img img-fluid" width="96" height="350" alt=""> </div>
                        </div>
                        <div class="card-body bg-light text-center">
                            <div class="mb-2">
                                <h6 class="font-weight-semibold mb-2"> <a href="#" class="text-default mb-2" data-abc="true"><%=pd.getProductName()%>   </a> </h6>
                            </div>
                            <%
                                if (pd.getListedPrice() == 0 || pd.getOriginalPrice() == pd.getListedPrice()) {
                            %>
                            <h3 class="mb-0 font-weight-semibold">$<%=pd.getOriginalPrice()%></h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <%
                            } else {
                            %>
                            <h6 class="mb-0 font-weight-semibold text-danger">WAS $<%=pd.getOriginalPrice()%></h6>
                            <h3 class="mb-0 font-weight-semibold text-success">NOW $<%=pd.getListedPrice()%></h3>
                            <div> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> <i class="fa fa-star star"></i> </div>
                            <%
                                }
                            %>

                            <!--IF WE ARE ON THE ACTIVE ADDS THIS BUTTON WILL BE A WITHDRAW BUTTON -->
                            <%
                                if (adds.equalsIgnoreCase("forSale")) {


                            %>
                            <a href='FactoryServlet?action=withdrawAdd&id=<%=pd.getId()%>'> <button type="button" class="btn btn-danger">WITHDRAW</button></a>
                            <a href='editAd.jsp?selectedAd=<%= pd.getId()%>&sellerId=<%= pd.getUserId()%>'> <button type="button" class="btn btn-primary">EDIT PRICE</button></a>
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


            <!--HERE WE WILL GIVE THE USER THE OPTION TO LIST A PRODUCT FROM THEIR PROFILE PAGE ALSO -->
            <%                //if its not equal to null check if it equals list item - did the user pick the list item from nav bar
                if (adds != null) {

                    if (adds.equals("listItem")) {

                        //toggle edit profile to false as we dont want to see that form if it is true

            %>

            <!--THE USER WISHES TO LIST AN ITEM FOR SALE -->
            <form action="FactoryServlet" method="POST" enctype="multipart/form-data">


                <div class="row">
                    <div class="col">
                        <input type="file" name="file" id="file" />
                    </div>
                    <div class="col">
                        <p>Make Sure To Select Your Product Image</p>
                    </div>
                </div>


                <div class="row">
                    <div class="col">
                        <input type="text" class="form-control" placeholder="Product Name" name="productName">
                    </div>
                    <div class="col">
                        <input type="text" class="form-control" placeholder="Price" name="price">
                    </div>
                </div>
                <div class="row mt-3">
                    <textarea class="col form-control " placeholder="Enter a Description..." name="description" size="10" maxlength="300" onblur="saveUserProgress" aria-required="true"></textarea>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <select class="form-control" name="colour">
                            <!--<option selected>Select a Colour</option>-->
                            <option selected value="1">Red</option>
                            <option value="2">Yellow</option>
                            <option value="3">Pink</option>
                            <option value="4">Purple</option>
                            <option value="5">Green</option>
                            <option value="6">Black</option>
                            <option value="7">White</option>
                            <option value="8">Navy</option>
                            <option value="9">Blue</option>
                        </select>
                    </div>
                    <!--WE WILL NEED A DAO FOR BRANDS AS THIS WILL BE A CONSTANTLY CHANGING TABLE-->
                    <%                        BrandDao bDao = new BrandDao("swapperz");
                        ArrayList<Brand> brandList = (ArrayList) bDao.getAllBrands();
                    %>
                    <div class="col">
                        <select class="form-control" name="brand">
                            <!--<option selected>Select a Brand</option>-->
                            <!--Loop through the brands-->
                            <%
                                for (Brand b : brandList) {

                            %>
                            <!--Get Id & Name-->
                            <option selected value="<%= b.getId()%>"><%= b.getName()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                    <!--WE WILL NEED A DAO FOR SIZES-->
                    <%
                        SizeDao sDao = new SizeDao("swapperz");
                        ArrayList<Size> sizeList = (ArrayList) sDao.getAllSizes();
                    %>
                    <div class="col">
                        <select class="form-control" name="size">
                            <!--<option selected>Select a Size</option>-->
                            <!--Loop through the brands-->
                            <%
                                for (Size s : sizeList) {

                            %>
                            <!--Get Id & Name-->
                            <option selected value="<%= s.getId()%>"><%= s.getSize()%></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="row mt-3">
                    <div class="col">
                        <select class="form-control" name="productCondition">
                            <option selected value="worn">Worn</option>
                            <option value="likenew">Like New</option>
                            <option value="brandnew">Brand New</option>
                        </select>
                    </div>
                    <div class="col">
                        <select class="form-control" name="productGender">
                            <option selected value="male">Male</option>
                            <option value="female">Female</option>
                            <option value="unisex">Unisex</option>
                        </select>
                    </div>
                    <div class="col">
                        <select class="form-control" name="productCategory">
                            <option selected value="menshoodies">Mens Hoodies</option>
                            <option value="menstrainers">Mens Trainers</option>
                            <option value="mensjackets">Mens Jackets</option>
                            <option value="mensjeans">Mens Jeans</option>
                            <option value="mensshirts">Mens Shirts</option>
                            <option value="womenstrainers">Womens Trainers</option>
                            <option value="womensJackets">Womens Jackets</option>
                        </select>
                    </div>
                </div>

                <!--tip for uploading with pics-->
                <div class="row float-right">
                    <div class="form-group">
                        <p class="text-secondary ">Ads with photos sell twice as fast!</p>
                    </div>
                </div>
                <br>
                <hr>

                <div class="row mt-5">
                    <div class="col text-center">
                        <input type="submit" class="btn btn-info btn-lg" value="Place Ad">
                    </div>
                </div>
                <input type="hidden" name="userId" value="<%= loggedInUser.getId()%>">
                <input type="hidden" name ="action" value="placeAd" />
            </form>

            <%
                    }
                }
            %>
            <%} else {%>
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
            <%
                }
            %>








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
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
                        Holder.addTheme('thumb', {
                            bg: '#55595c',
                            fg: '#eceeef',
                            text: 'Thumbnail'
                        });
        </script>
        <!--FONTAWSOME-->
        <script src="https://kit.fontawesome.com/030e034296.js" crossorigin="anonymous"></script>
    </body>
</html>
