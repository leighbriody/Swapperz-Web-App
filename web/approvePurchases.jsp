<%-- 
    Document   : approvePurchases
    Created on : 19-Mar-2021, 19:26:11
    Author     : Osama Kheireddine
--%>

<%@page import="business.Purchase"%>
<%@page import="daos.PurchaseDao"%>
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

            PurchaseDao pDao = new PurchaseDao("swapperz");
            ProductDao prodDao = new ProductDao("swapperz");
            ProductImageDao prodImageDao = new ProductImageDao("swapperz");
            ArrayList<Product> purchaseList = prodDao.unapprovedPurchases(); //gets all unapproved purchases

            //We need to get the cart and store it so that if the user has something already in their cart they cannot add it to cart again
            ArrayList<Product> cart = new ArrayList<Product>();

            User loggedInUser = (User) session.getAttribute("loggedInUser");
            //Now we need to see are admin user or regular user
            boolean isAdminUser = false;
            if (loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                isAdminUser = true;
            }

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
                
                if (loggedInUser != null && loggedInUser.getStatus().equalsIgnoreCase("admin")) {
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
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>

            <!--BANNER -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3 ">Unapproved Purchases</h1>
                </div>

            </div>
            <%
                // Get the error message variable out of the session
                Object msg = session.getAttribute("errorMessage");
                // Cast it to a String so we can use it
                String error = (String) msg;
                if (error != null) {
                    // Display the message
            %>

            <div class="text-danger"> <%=error%> </div>
            <%
                }
                session.removeAttribute("errorMessage");

                Object successMsg = session.getAttribute("successMessage");
                // Cast it to a String so we can use it
                String success = (String) successMsg;
                if (success != null) {
                    // Display the message
            %>

            <div class="text-success"> <%=success%> </div>
            <%
                }
                // We have finished with the results of this action
                // so now we can remove the value from the session
                session.removeAttribute("successMessage");
                // This makes sure that old error messages 
                // don't mistakenly get printed out later
            %> 


            <!--UNAPPROVED ITEMS THAT HAVE BEEN PURCHASED-->

            <div class="album py-5 bg-light">
                <div class="container">
                    <div class="row">
                        <%
                            if (purchaseList.size() == 0) {
                                //html to show sorry there are currently no products of this catego


                        %> 
                        <h1>No Pending Purchases</h1>
                        <%                        } else {
                            //we must loop trough pending purchases 
                            //as we have purchases we now need to get the id from the list and add them to 
                            //product list

                            ArrayList<Product> productList = new ArrayList();

//                            WE NEED A WAY TO STORE THE USER THAT BOUGHT AND THE PRODUCT ID
                            for (Product pur : purchaseList) {
                                //add a product to the list that has the productId
                                productList.add(prodDao.getProductById(pur.getId()));
                            }

                            int counter = 0;
                            for (Product p : productList) {

                                String productName = p.getProductName();
                                double price = p.getOriginalPrice();

                                //User object of the seller
                                User seller = uDao.getUserById(p.getUserId());
                                int imageId = p.getProductImage();

                                String imageLocation = prodImageDao.getProdcutImageLocation(imageId);
                                String img = imageLocation.replace("\\", "/");
                                if (p.getStatus().equalsIgnoreCase("pending")) {
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
                                    <form action="FactoryServlet" method="post">
                                        <div class="text-muted mb-3">
                                            <!--need seller id-->
                                            <input type="hidden" value="<%=seller.getId()%>"> 
                                            <p>Seller : <%=seller.getUsername()%></p>
                                        </div> 
                                        <a href="productDetails.jsp?productId=<%=p.getId()%>&sellerId=<%=p.getUserId()%>"></a>
                                        <!--
                                        get the hidden inputs to pass
                                        get userId
                                        get productId
                                        these will be used to approve the purchase 
                                        and then change the status from for sale to sold
                                        -->
                                        <input type="hidden" name="buyerId" value="<%= purchaseList.get(counter).getUserId()%>">
                                        <input type="hidden" name="sellerId" value="<%= p.getUserId()%>">
                                        <input type="hidden" name="productId" value="<%= purchaseList.get(counter).getId()%>">
                                        <input type="hidden" name="action" value="approvePurchase">
                                        <input type="submit" class="btn bg-cart btn-success" value="Approve">
                                    </form>
                                </div>
                            </div>
                        </div>
                        <%
                                        counter++;
                                    }
                                }
                            }

                        %>
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
                    <div class="jumbotron">
                        <h1>RESTRICTED ACCESS.</h1>
                    </div>
                    <%}%>


                </div>
                <br>
                <br>
                <br>
                <br>
                <br>
                <footer class="blog-footer text-center">
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
