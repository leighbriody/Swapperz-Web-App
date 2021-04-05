<%-- 
    Document   : editProfile
    Created on : 01-Mar-2021, 21:14:51
    Author     : ME
--%>
<%@page import="daos.UserDao"%>
<%@page import="business.Address"%>
<%@page import="daos.AddressDao"%>
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
                if (loggedInUser != null) {
                    
                    UserDao uDao = new UserDao("swapperz");
                    User u = uDao.getUserByUsername(loggedInUser.getUsername());
                    
                    String adds = "forSale";
                    adds = request.getParameter("adds");
                    //Create instance of the product dao
                    ProductDao p = new ProductDao("swapperz");
                    ProductImageDao prodImageDao = new ProductImageDao("swapperz");

                    boolean showActiveAds = false;
                    boolean showPreviousAds = false;
                    AddressDao aDao = new AddressDao("swapperz");
                    Address address = aDao.getUserAddress(loggedInUser.getId());

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
                        <div class="profile mr-3"><img src="<%= loggedInUser.getProfilePicture()%> " alt="..." width="130" class="rounded mb-2 img-thumbnail"><a href="editProfile.jsp" class="btn btn-outline-dark btn-sm btn-block">Edit profile</a></div>
                        <div class="media-body mb-5 text-white">

                            <h4 class="mt-0 mb-0 float-right mr-5">EDIT PROFILE</h4>

                            <p class="small mb-4"> <i class="fas fa-map-marker-alt mr-2"></i><%= address.getCountry() + "," + address.getCounty()%></p>
                        </div>
                    </div>
                </div>
                <div class="bg-light p-4 d-flex text-center">
                    <ul class="list-inline mb-0">
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=forSale">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "forSale")%></h5><small class="text-muted"> <i class="fas fa-image mr-1"></i>My Active Ads</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=sold">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "sold")%></h5><small class="text-muted"> <i class="fas fa-image ml-5 mr-1"></i>My Sold Ads</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=withdrawn">   <h5 class="font-weight-bold mb-0 d-block"><%= p.getNumOfUsersProductsOfStatus(loggedInUser.getId(), "withdrawn")%></h5><small class="text-muted"> <i class="fas fa-image ml-5 mr-1"></i>My Withdrawn Adds</small></a>
                        </li>
                        <li class="list-inline-item">
                            <a href= "myProfile.jsp?adds=listItem">  <h5 class="font-weight-bold mb-0 d-block">+</h5><small class="text-muted"> <i class="fas fa-image ml-5 mr-1"></i>LIST ITEM</small></a>
                        </li>
                        <li class="list-inline-item float-right">
                            <a href= "clothingPreferences.jsp">  <h5 class="font-weight-bold mb-0 d-block"><i class="fas fa-tshirt"></i></h5><small class="text-muted"> <i class="fas fa-user ml-5"></i>Clothing Preferences</small></a>
                        </li>
                    </ul>
                </div>

            </div>

            <!-- Will display the user's details & preferences -->

            <!--DETAILS-->
            <div class="container text-center">
                <div class="row d-inline-block p-2" id='userDetails'>
                    <form class="form" action="FactoryServlet" method="POST" enctype="multipart/form-data">
                        <!--need to get user details-->
                        <h4 class="mb-4">Personal Details:</h4>
                        <%
                            // Get the error message variable out of the session
                            Object successMsg = session.getAttribute("successMessage");
                            // Cast it to a String so we can use it
                            String success = (String) successMsg;
                            if (success != null) {
                                // Display the message
                        %>
                        <h4 class="text-success p-2"> <%=success%> </h4>
                        <%
                            }
                            // We have finished with the results of this action
                            // so now we can remove the value from the session
                            session.removeAttribute("successMessage");
                            // This makes sure that old error messages 
                            // don't mistakenly get printed out later
                        %>
                        <%
                            // Get the error message variable out of the session
                            Object msg = session.getAttribute("errorMessage");

                            // Cast it to a String so we can use it
                            String error = (String) msg;
                            if (error != null) {
                                // Display the message
                        %>

                        <h4 class="text-danger p-2"> <%=error%> </h4>
                        <%
                            }
                            // We have finished with the results of this action
                            // so now we can remove the value from the session
                            session.removeAttribute("errorMessage");
                            // This makes sure that old error messages 
                            // don't mistakenly get printed out later
                        %>
                        <div class="row p-2  text-center">
                            <div class="col-4 d-inline-block p-1">
                                <label class="font-weight-bold">Username:</label>
                                <input class="form-control" readonly type='text' name='username' value='<%= loggedInUser.getUsername()%>'>
                            </div>
                            <div class="col-4 d-inline-block p-1">
                                <label class="font-weight-bold">First Name:</label>
                                <input class="form-control" type='text' name='fName' value='<%= loggedInUser.getfName()%>'>
                            </div>
                            <div class="col-4 d-inline-block p-1">
                                <label class="font-weight-bold">Last Name:</label>
                                <input class="form-control" type='text' name='lName' value='<%= loggedInUser.getlName()%>'>
                            </div>
                        </div>
                        <div class="row p-2 text-center">
                            <div class=" col-6 d-inline p-1">
                                <label class="font-weight-bold">Email:</label>
                                <input class="form-control" type='email' name='email' value='<%= loggedInUser.getEmail()%>'>
                            </div>
                            <div class="col-6 d-inline-block p-1">
                                <label class="font-weight-bold">Phone:</label>
                                <input class="form-control" type='text' name='phone' value='<%= loggedInUser.getPhone()%>'>
                            </div>
                        </div>
                        <div class="row p-2 text-center">
                            <label class="font-weight-bold">Address Line 1:</label>
                            <input class="form-control " type='text' name='line1' value='<%= address.getAddrLine1()%>'>
                            <label class="font-weight-bold">Address Line 2:</label>
                            <input class="form-control " type='text' name='line2' value='<%= address.getAddrLine2()%>'>
                            <label class="font-weight-bold">Town:</label>
                            <input class="form-control " type='text' name='town' value='<%= address.getTown()%>'>
                        </div>
                        <div class="row p-2 text-center">
                            <div class="p-1">
                                <label class="font-weight-bold">County:</label>
                                <input class="form-control " type='text' name='county' value='<%= address.getCounty()%>'>
                            </div>
                        </div>
                        <div class="row p-2 text-center">
                            <div class="p-1">
                                <label class="font-weight-bold">Country:</label>
                                <input class="form-control " type='text' name='country' value='<%= address.getCountry()%>'>
                            </div>
                        </div>
                        <input type="submit" value="Change" class="btn btn-group-lg float-right btn-primary font-weight-bold">
                        <input type="hidden" name="action" value="editUserDetails">
                    </form>
                </div>
                <!--PREFERENCES-->
                <div class='row' id='userPreferences'>

                </div>
            </div>
            <%
            } else {%>
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
        <!--FONTAWSOME-->
        <script src="https://kit.fontawesome.com/030e034296.js" crossorigin="anonymous"></script>
    </body>
</html>
