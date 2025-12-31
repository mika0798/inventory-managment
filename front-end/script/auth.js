function isAuthenticated() {
    const token = localStorage.getItem('token');
    return token !== null && token !== undefined;
}

function  requireAuth() {
    if(!isAuthenticated()) {
        window.location.href = '/login.html';
    }
}