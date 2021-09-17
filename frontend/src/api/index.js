import axios from 'axios'

const instance = axios.create({
    // baseURL: '',
});

function registerUser(userData) {

    return instance.post('auth/signup', userData);
}

function signInUser(userData){
    // localStorage.setItem('X-AUTH-TOKEN',response. )
    return instance.post('auth/signin', userData);
}

function postList(){
    return instance.get('posts/1')
}

export { registerUser, signInUser, postList };