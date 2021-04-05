<%-- 
    Document   : checkout
    Created on : 06-Mar-2021, 16:31:25
    Author     : Osama Kheireddine
--%>
<%@page import="business.Size"%>
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
        <link rel="icon" href="/docs/4.0/assets/img/favicons/favicon.ico">

        <link rel="canonical" href="https://getbootstrap.com/docs/4.0/examples/checkout/">

        <!-- Bootstrap core CSS -->
        <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="form-validation.css" rel="stylesheet">
        <title>Swapperz - Checkout</title>

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
                        <a class="blog-header-logo text-dark shopName" href="#">Swapperz  </a>
                    </div>
                </div>
            </header>

            <!--FOR LOGIN TESTING TO SEE IF A USER IS LOGGED IN-->
            <% //Check if user logged in is not equal to null
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                ProductImageDao prodImgDao = new ProductImageDao("swapperz");
                ColourDao cDao = new ColourDao("swapperz");
                BrandDao bDao = new BrandDao("swapperz");
                SizeDao sDao = new SizeDao("swapperz");
                AddressDao aDao = new AddressDao("swapperz");

                if (loggedInUser != null) {
                    //GET THE PRODUCT BASED OFF THE CART ATTAY LIST IN THE SESSION
                    ArrayList<Product> cart = new ArrayList<>();

                    if (session.getAttribute("cart") == null) {
                        //they have somehow gotten to the checkout without adding item to cart or buy now
                    } else {
                        //ther is something in there cart to display
                        cart = (ArrayList<Product>) session.getAttribute("cart");
                    }

                    ProductDao pDao = new ProductDao("swapperz");
                    double totalCartCost = 0;

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
            <div class="text-center mt-4 mb-4">
                <h1>CHECKOUT</h1>
                <h1><i class="fas fa-shopping-cart"></i></h1>
            </div>
            <br>
            <div class="container">




                <!--SHOPPING CART ITEMS HERE -->
                <!--SHOPPING CART HERE --> <div class="row">
                    <div class="col-lg-12 p-5 bg-white rounded shadow-sm mb-5">

                        <!-- Shopping cart table -->
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="p-2 px-3 text-uppercase">Product</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Price</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Colour</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Brand</div>
                                        </th>

                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Size</div>
                                        </th>

                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Condition</div>
                                        </th>
                                        <th scope="col" class="border-0 bg-light">
                                            <div class="py-2 text-uppercase">Remove</div>
                                        </th>
                                    </tr>


                                </thead>
                                <tbody>


                                    <!--WANT TO LOOP AROUND EACH PRODUCT IN THE CART -->
                                    <%
                                        int swapperzFee = 0;
                                        double total = 0;

                                        if (cart.size() > 0) {
                                            //1 euro swapperz fee

                                            for (Product p : cart) {

                                                //If the listed price is 0 it menas its not discounted so just at its original price
                                                double productPrice = 0;
                                                productPrice = p.getListedPrice();
                                                if (productPrice == 0) {
                                                    productPrice = p.getOriginalPrice();
                                                }
                                                swapperzFee++;

                                                //For each product we also have a transaction fee of 1 euro
                                                totalCartCost += productPrice;


                                    %>


                                    <tr>
                                        <th scope="row" class="border-0">
                                            <div class="p-2">
                                                <img src="<%=prodImgDao.getProdcutImageLocation(p.getProductImage())%>" alt="" width="70" class="img-fluid rounded shadow-sm">
                                                <div class="ml-3 d-inline-block align-middle">
                                                    <h5 class="mb-0"> <a href="#" class="text-dark d-inline-block align-middle"> <%=p.getProductName()%></a></h5><span class="text-muted font-weight-normal font-italic d-block">Category: <%=p.getProductCategory()%></span>
                                                </div>
                                            </div>
                                        </th>
                                        <td class="border-0 align-middle"><strong>$<%=productPrice%></strong></td>
                                        <td class="border-0 align-middle"><strong><%=cDao.getColour(p.getColour()).getColour()%></strong></td>
                                        <td class="border-0 align-middle"><strong><%=bDao.getBrand(p.getBrand()).getName()%></strong></td>
                                        <td class="border-0 align-middle"><strong><%=sDao.getSize(p.getSize()).getSize()%></strong></td>
                                        <td class="border-0 align-middle"><strong><%=p.getProductCondition()%></strong></td>
                                        <td class="border-0 align-middle"><a href="FactoryServlet?action=removeItemFromCart&productId=<%=p.getId()%>&cart=<%=cart%>" class="text-dark"><i class="fa fa-trash"></i></a></td>
                                    </tr>
                                    <%}

                                        total = totalCartCost + swapperzFee;
                                    } else {%>

                                <h2>Looks like your cart is empty ! </h2>

                                <%}%>
                                </tbody>
                            </table>

                        </div>
                        <!-- End -->
                        <!--SHOPPING CART ORDER SUMMARY HERE --> <div class="row py-5 p-4 bg-white rounded shadow-sm">

                            <div class="col-lg-12">
                                <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Order summary </div>
                                <div class="p-4">
                                    <p class="font-italic mb-4">At swapperz we charge a small transaction fee of $1 per order to ensure we can keep delivering this service .</p>
                                    <ul class="list-unstyled mb-4">
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Order Subtotal </strong><strong>$ <%=totalCartCost%></strong></li>
                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Swapperz Transaction Fee</strong><strong>$<%=swapperzFee%></strong></li>

                                        <li class="d-flex justify-content-between py-3 border-bottom"><strong class="text-muted">Total</strong>
                                            <h5 class="font-weight-bold">$<%=total%></h5>
                                        </li>
                                    </ul>
                                    <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Billing Information </div>
                                    <div class="row">
                                        <div class="col-md-6 mb-3">
                                            <label for="firstName">First name</label>
                                            <input type="text" class="form-control" id="firstName"  placeholder="<%=loggedInUser.getfName()%>" value="" readonly>
                                            <div class="invalid-feedback">
                                                Valid first name is required.
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-3">
                                            <label for="lastName">Last name</label>
                                            <input type="text" class="form-control" id="lastName" name="lName" placeholder="<%=loggedInUser.getlName()%>" value="" readonly>
                                            <div class="invalid-feedback">
                                                Valid last name is required.
                                            </div>
                                        </div>

                                        <div class="col-12 mb-3">
                                            <label for="email">Email</label>
                                            <input type="email" class="form-control" id="email" name="email" placeholder="<%=loggedInUser.getEmail()%>" readonly>
                                            <div class="invalid-feedback">
                                                Please enter a valid email address for shipping updates.
                                            </div>
                                        </div>


                                        <div class="col-md-6 mb-3">
                                            <label for="address">Address Line 1</label>
                                            <input type="text" class="form-control" id="address" name="line1" placeholder="<%=aDao.getUserAddress(loggedInUser.getId()).getAddrLine1()%>" readonly>

                                        </div>

                                        <div class="col-md-6 mb-3">
                                            <label for="address">Address Line 2</label>
                                            <input type="text" class="form-control" id="address" name="line1" placeholder="<%=aDao.getUserAddress(loggedInUser.getId()).getAddrLine2()%>" readonly>

                                        </div>

                                        <div class="col-md-6 mb-3">
                                            <label for="address">Town</label>
                                            <input type="text" class="form-control" id="address" name="line1" placeholder="<%=aDao.getUserAddress(loggedInUser.getId()).getTown()%>" readonly>

                                        </div>



                                        <div class="col-md-6 mb-3">
                                            <label for="address">County</label>
                                            <input type="text" class="form-control" id="address" name="line1" placeholder="<%=aDao.getUserAddress(loggedInUser.getId()).getCounty()%>" readonly>

                                        </div>

                                        <div class="col-12 mb-3">
                                            <label for="address">Country</label>
                                            <input type="text" class="form-control" id="address" name="line1" placeholder="<%=aDao.getUserAddress(loggedInUser.getId()).getCountry()%>" readonly>

                                        </div>



                                    </div>
                                </div>
                            </div>
                        </div>
                        <!--PAYMEN DETAILS -->
                        <div class="bg-light rounded-pill px-4 py-3 text-uppercase font-weight-bold">Payment </div>

                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label for="firstName">Name On Card</label>
                                <input type="text" class="form-control" id="firstName"  placeholder="" value="" required>

                            </div>

                            <div class="col-md-6 mb-3">
                                <label for="firstName">Card Number</label>
                                <input type="text" class="form-control" id="firstName"  placeholder="" value="" required>

                            </div>
                            <div class="col-md-6 mb-3 ">
                                <label for="firstName">Expiration</label>
                                <input type="text" class="form-control" id="firstName"  placeholder="" value="" required>

                            </div>
                            <div class="col-md-6 mb-3 ">
                                <label for="firstName">CVV</label>
                                <input type="text" class="form-control" id="firstName"  placeholder="" value="" required>

                            </div>
                        </div>

                    </div>
                </div>





                <hr class="mb-4">
                <form class="form-signin text-center" action="FactoryServlet" method="post">
                <input type="hidden" name="action" value="checkout">
                <input type="hidden" name="totalCost" value="<%=total %>">
                <input type="hidden" name="userId" value="<%= loggedInUser.getId()%>">
                <input class="btn btn-primary btn-lg btn-block" type="submit" value="Continue to checkout">
                </form>
            </div>
        </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery-slim.min.js"><\/script>')</script>
    <script src="../../assets/js/vendor/popper.min.js"></script>
    <script src="../../dist/js/bootstrap.min.js"></script>
    <script src="../../assets/js/vendor/holder.min.js"></script>
    <script>
        // Example starter JavaScript for disabling form submissions if there are invalid fields
        (function () {
            'use strict';

            window.addEventListener('load', function () {
                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.getElementsByClassName('needs-validation');

                // Loop over them and prevent submission
                var validation = Array.prototype.filter.call(forms, function (form) {
                    form.addEventListener('submit', function (event) {
                        if (form.checkValidity() === false) {
                            event.preventDefault();
                            event.stopPropagation();
                        }
                        form.classList.add('was-validated');
                    }, false);
                });
            }, false);
        })();
    </script>


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

    <!--BANNER -->
    <div class="row mb-2 jumbotron-grid">
        <div class="col-md-6">



            <!--------------------------------------------------->
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
<!--FONTAWSOME-->
<script src="https://kit.fontawesome.com/030e034296.js" crossorigin="anonymous"></script>
</body>
</html>
