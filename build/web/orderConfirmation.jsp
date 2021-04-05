<%-- 
    Document   : orderConfirmation
    Created on : 08-Mar-2021, 20:01:19
    Author     : ME
--%>

<%@page import="business.Address"%>
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
        <link href="style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5 mb-5">
            <header class="blog-header py-3">
                <div class="row flex-nowrap justify-content-between align-items-center">

                    <div class="col-12 text-center">
                        <a class="blog-header-logo text-dark shopName" href="#">Swapperz</a>
                    </div>

                </div>
            </header>
            <!--FOR LOGIN TESTING TO SEE IF A USER IS LOGGED IN-->
            <% //Check if user logged in is not equal to null
                User u = (User) session.getAttribute("loggedInUser");
                if (u != null) {
                    AddressDao aDao = new AddressDao("swapperz");
                    Address a = aDao.getUserAddress(u.getId());
                    ProductImageDao prodImgDao = new ProductImageDao("swapperz");
                    BrandDao bDao = new BrandDao("swapperz");
                    ColourDao cDao = new ColourDao("swapperz");
                    SizeDao sDao = new SizeDao("swapperz");
                    
                    
            %>
            <div class="nav-scroller py-1 mb-4">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted " href="upgradeToPro.jsp">GO PRO</a>
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=u.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>
            <%
                //get the product they ordered
                ArrayList<Product> ordersConfirmed = (ArrayList<Product>) session.getAttribute("cart");

                //CHANGE ABOVE TO ACCEPT ARRAYLIST FOR MULTIPLE PRODUCTS
                //NOTE WE NEED TO CHANGE THE CHECKOUT TO PASS THROUGH AN ARRAYLIST
//                    ArrayList<Product> productList = (ArrayList<Product>) session.getAttribute("orderConfirmationList");
                //we have to make sure they have an order confirmed
                if (ordersConfirmed.size() > 0) {
            %>
            <div class="row d-flex justify-content-center mt-3">
                <div class="col-md-8">
                    <div class="card">
                        <div class="text-left logo p-2 px-5"> <img src="images/swapperzLogoLight.ico" width="50"> </div>
                        <div class="invoice p-5">
                            <h5>Order Confirmed!</h5> <span class="font-weight-bold d-block mt-4">Hello, <%= u.getfName().substring(0, 1).toUpperCase()%><%= u.getfName().substring(1).toLowerCase()%></span> <span>You order has been confirmed!</span>
                            <div class="payment border-top mt-3 mb-3 border-bottom table-responsive">
                                <table class="table table-borderless">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <div class="py-2"> <span class="d-block text-muted">Order No</span> <span></span> </div>
                                            </td>
                                            <td>
                                                <div class="py-2"> <span class="d-block text-muted">Shiping Address</span> <span><%= a.getAddrLine1()%>, <%= a.getTown()%>,<%= a.getCounty()%>, <%= a.getCountry()%></span> </div>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="product border-bottom table-responsive">
                                <table class="table table-borderless">
                                    <tbody>
                                        <!--PASS THROUGH AN ARRAYLIST AND USE THAT IF THERE IS MORE THAN ONE PRODUCT BOUGHT-->
                                        <!--THEN WE CAN JUST LOOP FROM HERE-->
                                        <%
                                            for (Product p : ordersConfirmed) {
                                        %>
                                        <tr>
                                            <td width="20%"> <img src="<%=prodImgDao.getProdcutImageLocation(p.getProductImage())%>" width="90"> </td>
                                            <td width="60%"> <span class="font-weight-bold"><%= p.getProductName()%></span></td>
                                            <td width="60%"> <span class="font-weight-bold"><%=sDao.getSize(p.getSize()).getSize()%></span></td>
                                                <%
                                                    if (p.getListedPrice() == 0) {
                                                %>
                                            <td width="20%">
                                                <div class="text-right"> <span class="font-weight-bold">$<%= p.getOriginalPrice()%></span> </div>
                                            </td>
                                            <%
                                            } else {
                                            %>
                                            <td width="20%">
                                                <div class="text-right"> <span class="font-weight-bold">$<%= p.getListedPrice()%></span> </div>
                                            </td>
                                            <%
                                                }
                                            %>
                                        </tr>
                                        <%
                                            }
                                        %>
                                        <!--TO HERE!!!!!!!!-->
                                    </tbody>
                                </table>
                            </div>

                            <p>We will be sending shipping confirmation email when the item shipped successfully!</p>
                            <p class="font-weight-bold mb-0">Thanks for shopping with us!</p> <span>Swapperz Team</span>
                        </div>
                    </div>
                </div>
            </div>



            <%}
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
    </body>
</html>
