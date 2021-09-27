import axios from 'axios'

const instance = axios.create({
//     baseURL: "http://localhost:8080"
    headers: {
        authtoken: localStorage.getItem("token"), // header의 속성
    },
});

function registerUser(userData) {
    return instance.post('auth/signup', userData);
}

function signInUser(userData){
    // localStorage.setItem('X-AUTH-TOKEN',response. )
    return instance.post('auth/signin', userData);
}

function getMyInfoApi() {
    return instance.get("auth/mypage");
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

function deletePost(postId){
    return instance.delete(`/post/`+postId);
}

function updatePost(postData, postId){
    console.log(localStorage.getItem('token'));
    return instance.put(`/post/`+ postId, postData);
}

// function getPost(postData){
//     return instance.get('post/'+postData.post);
// }

export { registerUser, signInUser, createPost, getMyInfoApi, updatePost, deletePost};