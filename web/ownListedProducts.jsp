<%-- 
    Document   : ownListedProduct
    Created on : 17-Feb-2021, 17:27:22
    Author     : ME
--%>

<%@page import="business.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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


        <!--TO DETERMINE IF THE PLACE AD COMMAND WAS A SSUCCESS -->
        <%
            boolean addPlaced = false;
            // Get the error message variable out of the session
            Object msg = session.getAttribute("successMessage");
            // Cast it to a String so we can use it
            String success = (String) msg;
            if (success != null) {
                //set flag to true
                addPlaced = true;

            } else {
                // We have finished with the results of this action
                // so now we can remove the value from the session
                session.removeAttribute("errorMessage");
                // This makes sure that old error messages 
                // don't mistakenly get printed out later
            }
        %> 





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


            <!--NOW IF THE USER IS AN ADMIN OR NOT WE NEED TO DISPLAY DIFFERNT NAVBARS -->
            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted active" href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="pickMensCategory.jsp">MEN</a>
                    <a class="p-2 text-muted" href="pickWomensCategory.jsp">WOMEN</a>
                    <a class="p-2 text-muted" href="sellProduct.jsp">SELL</a>
                    <a class="p-2 text-muted " href="upgradeToPro.jsp">GO PRO</a>
                    <a class="p-2 text-muted " href="cart.jsp">CART</a>
                    <a class="p-2 text-muted" href="myProfile.jsp"><%=loggedInUser.getUsername().toUpperCase()%></a>
                    <a  class="p-2 text-dark bold" href="FactoryServlet?action=logout">Logout</a>
                </nav>
            </div>

            <%
                if (addPlaced) {

            %>
            <!--SUCCESS OR ERROR MESSAGE HERE -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-12 ">Your Add Was Listed Successfully !!</h1>
                    <p class="lead my-3">You can view or modify the newly placed add on your profile ! </p>
                    
                    <!--------------------------------------------------->
                </div>
                <div class="col-md-6 ">
                    <img class="blackHoodie" src="images/tick.png">

                </div>
            </div>

            <%} else {%>
            <!--THE ADD WAS NOT PLACED SUCCESSFULLY SO SHOW THE ERROR MESSAGE -->


            <%}%>


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
            <%
                if (addPlaced) {

            %>
            <!--SUCCESS OR ERROR MESSAGE HERE -->
            <div class="row mb-2 jumbotron-grid">
                <div class="col-md-6">
                    <h1 class="display-3 ">YOUR ADD WAS PLACED SUCCESSFULLY !</h1>
                    <p class="lead my-3">The leading application in buying and selling clothes</p>
                    <p class="lead mb-0"><a href="register.jsp" class="text-white font-weight-bold"><button class="btn btn-primary">Get Me Started</button></a></p>
                    <!--------------------------------------------------->
                </div>
                <div class="col-md-6 ">
                    <img class="blackHoodie" src="images/blackHoodieNoBg.png">

                </div>
            </div>

            <%} else {%>
            <!--THE ADD WAS NOT PLACED SUCCESSFULLY SO SHOW THE ERROR MESSAGE


            <%}%>
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
