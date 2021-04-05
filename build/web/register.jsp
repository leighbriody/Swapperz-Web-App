<%@page import="business.SecurityQuestions"%>
<%@page import="java.util.ArrayList"%>
<%@page import="daos.SecurityQuestionsDao"%>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="shortcut icon" href="images/swapperzLogoLight.ico" type="image/x-icon" />

        <title>Swapperz - Register </title>

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
                        <a class="blog-header-logo text-dark" href="#">Swapperz</a>
                    </div>

                </div>
            </header>

            <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-between">
                    <a class="p-2 text-muted " href="index.jsp">HOME</a>
                    <a class="p-2 text-muted" href="#">MEN</a>
                    <a class="p-2 text-muted" href="#">WOMEN</a>
                    <a class="p-2 text-muted" href="login.jsp">LOGIN</a>
                    <a class="p-2 text-muted active" href="register.jsp">REGISTER</a>

                </nav>
            </div>
            <main role="main">
                <form class="form-signin text-center" action="FactoryServlet" method="post" enctype="multipart/form-data">
                    <img class="mb-4" src="images/loginGuy.jpg" alt="" width="50%" height="50%" >
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
                    <h1 class="h3 mb-3 font-weight-normal">Register</h1>


                    <!--ALL INPUT FIELDS NEEDED FOR THE REGISTER FORM -->
                    <label  class="float-left">Profile Picture :</label>
                    <input type="file" id="inputProfilePic"  class="form-control"  required autofocus name="profilePic">
                    <label  class="float-left">First Name :</label>
                    <input type="text" id="inputFirstName" class="form-control" placeholder="First Name" required autofocus name="fName">
                    <label  class="float-left">Last Name :</label>
                    <input type="text" id="inputLastName" class="form-control" placeholder="Last Name" required name="lName">
                    <label  class="float-left">Email :</label>
                    <input type="email" id="inputEmail" class="form-control" placeholder="Email" required name="email">
                    <label  class="float-left">Phone :</label>
                    <input type="text" id="inputPhone" class="form-control" placeholder="Phone" required name="phone">
                    <label  class="float-left">Address Line 1 :</label>
                    <input type="text" id="inputAddressLine1" class="form-control" placeholder="Address Line 1" required name="addrLine1">
                    <label  class="float-left">Address Line 2 :</label>
                    <input type="text" id="inputAddressLine2" class="form-control" placeholder="Address Line 2" required name="addrLine2">
                    <label  class="float-left">Town :</label>
                    <input type="text" id="inputTown" class="form-control" placeholder="Town" required name="town">
                    <label  class="float-left">County :</label>
                    <input type="text" id="inputCounty" class="form-control" placeholder="County" required name="county">
                    <label  class="float-left">Country :</label>
                    <input type="text" id="Country" class="form-control" placeholder="Country" required name="country">
                    <label  class="float-left">Gender :</label>
                    <select class="form-control form-select-lg " aria-label="Default select example" name="gender">
                        <option selected>Gender</option>
                        <option value="male">Male</option>
                        <option value="female">Female</option>
                    </select>

                    <!--SECURITY QUESTIONS -->
                    <%
                        //here we will retrieve all of the security questions from the db
                        //create a security question object
                        SecurityQuestionsDao question = new SecurityQuestionsDao("swapperz");
                        //create an arrayList to populate (populate while initalising)
                        ArrayList<SecurityQuestions> questionList = (ArrayList) question.getAllQuestions();
                    %>


                    <!--QUESTION 1 -->
                    <label  class="float-left">Security Question 1 :</label>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                        </div>
                        <select class="form-control" name="securityQuestion1" required>
                            <!--start loop-->
                            <%
                                for (SecurityQuestions s : questionList) {
                            %>
                            <!--get the id in the value-->
                            <!--display the question as text in the option-->
                            <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                            <%
                                    //end of loop
                                }
                            %>

                        </select>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                            </div>
                            <input name="answer1"class="form-control" placeholder="Answer" required size=130 type="text" required/>
                        </div>

                    </div>


                    <!--QUESTION 2  -->
                    <label  class="float-left">Security Question 2 :</label>

                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                        </div>
                        <select class="form-control" name="securityQuestion2" required>
                            <!--start loop-->
                            <%
                                for (SecurityQuestions s : questionList) {
                            %>
                            <!--get the id in the value-->
                            <!--display the question as text in the option-->
                            <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                            <%
                                    //end of loop
                                }
                            %>

                        </select>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                            </div>
                            <input name="answer2" class="form-control"  placeholder="Answer" required size=130 type="text" required/>
                        </div>

                    </div>

                    <!--QUESTION 3  -->
                    <label  class="float-left">Security Question 3 :</label>
                    <div class="form-group input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"> <i class="fa fa-building"></i> </span>
                        </div>
                        <select class="form-control" name="securityQuestion3" required>
                            <!--start loop-->
                            <%
                                for (SecurityQuestions s : questionList) {
                            %>
                            <!--get the id in the value-->
                            <!--display the question as text in the option-->
                            <option value="<%= s.getId()%>"><%= s.getQuestion()%> </option>
                            <%
                                    //end of loop
                                }
                            %>

                        </select>
                        <div class="form-group input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text"> <i class="fa fa-user"></i> </span>
                            </div>
                            <input name="answer3" class="form-control"  placeholder="Answer" required size=130 type="text" required/>
                        </div>

                    </div>

                    <label  class="float-left">Username :</label>
                    <input type="text" id="inputUsername" class="form-control" placeholder="Username" required autofocus name="username">

                    <label  class="float-left">Password :</label>
                    <input type="password" id="inputPassword" class="form-control" placeholder="Password" required onchange='check_pass();' name="password">

                    <label  class="float-left">Confirm Password:</label>
                    <input type="password" id="inputPassword2" class="form-control" placeholder="Password" required onchange='check_pass();' name="password2">
                    <span id='message' ></span>

                    <!--SECURITY QUESTIONS-->

                    <button class="btn btn-lg btn-primary btn-block" type="submit" value="register" name="action">Register</button>
                    <hr>


                </form>


            </main>




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
        <script>
                        Holder.addTheme('thumb', {
                            bg: '#55595c',
                            fg: '#eceeef',
                            text: 'Thumbnail'
                        });

                        function check_pass() {
                            if (document.getElementById('inputPassword').value ==
                                    document.getElementById('inputPassword2').value) {
                                document.getElementById('submit').disabled = false;
                            } else {
                                document.getElementById('submit').disabled = true;
                            }
                        }

        </script>
    </body>
</html>



