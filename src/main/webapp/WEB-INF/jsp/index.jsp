<!DOCTYPE HTML>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="css/fc_basic.css" />

        <title>Flashcard Application</title>

    </head>
    <body>
        <header>
            <h1>Flashcard Application</h1>
        </header>
        <nav>
            <ul>
                <li><a href="#">Help</a></li>
                <li><a href="#">About</a></li>
                <li><a href="#">Contact</a></li>
            </ul>
        </nav>

        <div id="content">
            <div id="mainContent">
                <section>
                    Welcome to Flashcard Application designed and developed to ease studying.<br /> The hardest thing in the world! ...
                </section>
            </div>
            <aside>
                <section id="loginForm">
                    <form action="login.do" method="get">
                        <table>
                            <tr>
                                <td>E-mail: </td>
                                <td><input type="email" name="email" size="25" required placeholder="Use only valid email" /></td>
                            </tr>
                            <tr>
                                <td>Password: </td>
                                <td><input type="password" name="password" size="25" required placeholder="Password"/></td>
                            </tr>
                        </table>

                        <p><input type="submit" name="submit" id="submit" value="Login"/>
                            <input type="reset" name="reset" id="reset" value="Reset"/>
                        </p>
                    </form>
                </section>
            </aside>
        </div>

        <footer>
            <p>All rights reserved</p>
        </footer>
    </body>
</html>