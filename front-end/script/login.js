const apiURL = 'http://localhost:8080';

function login(event) {
    event.preventDefault();
    let usernameReq = document.getElementById('username').value;
    let passwordReq = document.getElementById('password').value;
    let data = {username : usernameReq, password: passwordReq};
    let url = `${apiURL}/login`
    fetch(url, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(data)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Invalid username or password");
            }
            return response.text();
        })
        .then(token => {
            localStorage.setItem('token', token);
            alert("Logged in successfully");
            window.location.href= '/index.html'
        });
}