<%-- 
    Document   : sellProduct
    Created on : 16-Feb-2021, 15:21:18
    Author     : Osama Kheireddine
--%>

<%@page import="business.Size"%>
<%@page import="daos.SizeDao"%>
<%@page import="business.Brand"%>
<%@page import="java.util.ArrayList"%>
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

        <title>Swapperz - Place an Ad </title>

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

            <% //Check if user logged in is not equal to null
                User loggedInUser = (User) session.getAttribute("loggedInUser");
                
                 //Now we need to see are admin user or regular user
                boolean isAdminUser = false;
                if (loggedInUser.getStatus().equalsIgnoreCase("admin")) {
                    isAdminUser = true;
                }
                
                if (loggedInUser != null) {
            %>
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">            
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted " href="pickWomensCategory.jsp">WOMEN</a>
                      <a class="p-2 text-muted active " href="sellProduct.jsp">SELL</a>
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
                        <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase() %></a>
                      <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>

            <!--BANNER -->
            <div class="row mb-2 jumbotron-grid text-center">
                <div class="col-md-12">
                    <h1 class="display-3">Place an Ad</h1>
                </div>
                <div class="col-md-6 ">

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
                // We have finished with the results of this action
                // so now we can remove the value from the session
                session.removeAttribute("errorMessage");
                // This makes sure that old error messages 
                // don't mistakenly get printed out later
            %> 

            <main role="main">




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
                             <label  class="float-left">Product Name :</label>
                            <input type="text" class="form-control" placeholder="Product Name" name="productName">
                        </div>
                        <div class="col">
                             <label  class="float-left">Product Price :</label>
                            <input type="text" class="form-control" placeholder="Price" name="price">
                        </div>
                    </div>
                    
                    <div class="col">
                          <label  class="float-left">Product Description :</label>
                        <textarea class="col form-control " placeholder="Enter a Description..." name="description" size="10" maxlength="300" onblur="saveUserProgress" aria-required="true"></textarea>
                    </div>
                    <div class="row mt-3">
                        <div class="col">
                             <label  class="float-left">Product Colour :</label>
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
                        <%
                            BrandDao bDao = new BrandDao("swapperz");
                            ArrayList<Brand> brandList = (ArrayList) bDao.getAllBrands();
                        %>
                        <div class="col">
                             <label  class="float-left">Product Brand :</label>
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
                             <label  class="float-left">Product Size :</label>
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
                             <label  class="float-left">Product Condition :</label>
                            <select class="form-control" name="productCondition">
                                <option selected value="worn">Worn</option>
                                <option value="likenew">Like New</option>
                                <option value="brandnew">Brand New</option>
                            </select>
                        </div>
                        <div class="col">
                             <label  class="float-left">Product Gender :</label>
                            <select class="form-control" name="productGender">
                                <option selected value="male">Male</option>
                                <option value="female">Female</option>
                                <option value="unisex">Unisex</option>
                            </select>
                        </div>
                        <div class="col">
                             <label  class="float-left">Product Category :</label>
                            <select class="form-control" name="productCategory">
                                <option selected value="mens hoodies">Mens Hoodies</option>
                                <option value="mens trainers">Mens Trainers</option>
                                <option value="mens jackets">Mens Jackets</option>
                                <option value="mens jeans">Mens Jeans</option>
                                <option value="mens shirts">Mens Shirts</option>
                                 <option value="mens accessories">Mens Accessories</option>
                                <option value="womens trainers">Womens Trainers</option>
                                <option value="womens jackets">Womens Jackets</option>
                                <option value="womens hoodies">Womens Hoodies</option>
                                 <option value="womens jeans">Womens Jeans</option>
                                 <option value="womens leggings">Womens Leggings</option>
                                 <option value="womens accessories">Womens Accessories</option>
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
                    <!--show the user their details that this ad will be posted under-->
                    <h3 class="text-center">Contact Information</h3>
                    <h5 class="text-center">(How the buyer will see you)</h5>
                    <br>
                    <!--Name & Contact (mobile)-->
                    <h4>Name and Contact:</h4>
                    <div class="row">
                        <div class="col">
                            <label class="blockquote">Your Name:</label>
                            <p class="form-control"><%= loggedInUser.getfName()%>  <%= loggedInUser.getlName()%></p>
                        </div>
                        <div class="col">
                            <label class="blockquote">Your Phone Number:</label>
                            <p class="form-control"><%= loggedInUser.getPhone()%></p>
                        </div>
                    </div>
                    <!--Address (if a user wants to collect)-->
                    <h4>Address:</h4>
                    <div class="row">
                        <div class="col">
                            <!--ADDRESS-->
                            <label class="blockquote">Line 1:</label>
                            <p class="form-control"><%= loggedInUser.getAddrLine1()%></p>
                            <label class="blockquote">Line 2:</label>
                            <p class="form-control"><%= loggedInUser.getAddrLine2()%></p>
                        </div>
                        <div class="col">
                            <label class="blockquote">County:</label>
                            <p class="form-control"><%= loggedInUser.getCounty()%></p>
                            <label class="blockquote">Country:</label>
                            <p class="form-control"><%= loggedInUser.getCountry()%></p>
                        </div>
                    </div>
                    <div class="row mt-5">
                        <div class="col text-center">
                            <input type="submit" class="btn btn-info btn-lg" value="Place Ad">
                        </div>
                    </div>
                    <input type="hidden" name="userId" value="<%= loggedInUser.getId()%>">
                    <input type="hidden" name ="action" value="placeAd" />
                </form>
            </main>
            <br>
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


            <footer class="blog-footer">

                <p>
                    <a href="#">Back to top</a>
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
    </body>
</html>

