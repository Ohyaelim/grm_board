import axios from 'axios'

const instance = axios.create({
//     baseURL: "http://localhost:8080"
});

function registerUser(userData) {
    return instance.post('auth/signup', userData);
}

function signInUser(userData){
    // localStorage.setItem('X-AUTH-TOKEN',response. )
    return instance.post('auth/signin', userData);
}

// function postList(){
//     return instance.get('posts/1');
// }
//
// function myPage(userData){
//     return instance.get('auth/mypage', userData);
// }

function createPost(postData){
    console.log(localStorage.getItem('token'));
    return instance.post(`post/`+postData.board, postData);
}

export { registerUser, signInUser, createPost};