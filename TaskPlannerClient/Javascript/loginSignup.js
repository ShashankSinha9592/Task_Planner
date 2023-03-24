// url = https://taskplanner-production-b2a1.up.railway.app/taskscheduler/user = post , signup, return type user

// url = https://taskplanner-production-b2a1.up.railway.app/taskscheduler/login = post, login  return type token

class User {
  constructor() {}
  #checkPassword(str) {
    return str.length >= 6 && str.length <= 12;
  }
  #checkUsername(str) {
    if (
      str.includes("#") ||
      str.includes("*") ||
      str.includes("%") ||
      str.includes("$")
    ) {
      return false;
    }
    return true;
  }
  async Signup(firstName, lastName, email, password) {
    try {
      if (
        this.#checkPassword(password) &&
        this.#checkUsername(firstName) &&
        this.#checkUsername(lastName)
      ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        let obj = {
          firstName,
          lastName,
          email,
          password,
        };

        let url = `http://localhost:8080/taskscheduler/user`;
        console.log(JSON.stringify(obj));
        let res = await fetch(url, {
          method: "POST",
          body: JSON.stringify(obj),
          headers: {
            "Content-Type": "application/json",
          },
        });
        let data = await res.json();
        console.log(data);
        alert("SignUp Success");
        toggle();
      }
    } catch (err) {
      alert(err);
    }
  }

  async #Authenticate(token, username) {
    let url = `https://masai-api-mocker.herokuapp.com/user/${username}`;
    try {
      let res = await fetch(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });
      localStorage.setItem("name", JSON.stringify(username[0]));
      location.href = "./index.html";
    } catch (err) {
      alert("Invalid Credentials");
    }
  }
  async Login(email, password) {
    let myData = {
      email,
      password,
    };
    try {
      let url = `https://taskplanner-production-b2a1.up.railway.app/taskscheduler/login`;

      let res = await fetch(url, {
        method: "POST",
        body: JSON.stringify(myData),
        headers: {
          "Content-Type": "application/json",
        },
      });
      let data = await res.json();
      //   this.#Authenticate(data.token, myData.username);
      console.log(data);
    } catch (err) {
      alert("Invalid Credentials");
    }
  }
}

let container = document.getElementById("container");

toggle = () => {
  container.classList.toggle("sign-in");
  container.classList.toggle("sign-up");
};

setTimeout(() => {
  container.classList.add("sign-in");
}, 200);

function select(el) {
  return document.querySelector(el).value;
}

let user = new User();
function Register() {
  let firstName = select("#firstName");
  let lastName = select("#lastName");
  let email = select("#email");
  let password = select("#password");

  console.log(firstName, " ", lastName, " ", email, " ", password);
  user.Signup(firstName, lastName, email, password);
}

function LoginUser() {
  let email = select("#userLogin");
  let password = select("#passwordLogin");

  user.Login(email, password);
}
