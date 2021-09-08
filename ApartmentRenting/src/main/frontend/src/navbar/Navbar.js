import React from 'react';
import { useHistory } from 'react-router-dom';

const Navbar = ({setLoginUser, loginUser}) => {

const history = useHistory()

const logOut = () => {
    setLoginUser("");
    localStorage.setItem("loginUser", "");
    history.push("/");
}

return (
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="#">Apartment Buddy</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
    </ul>
    <form class="form-inline my-2 my-lg-0" onSubmit={logOut}>
      { loginUser !== "" && <button className="btn bg-light my-2 my-sm-0" type="submit">Logout</button>}
    </form>
  </div>
</nav>)
}

export default Navbar