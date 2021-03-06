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

function updateMyPage(infoData){
    return instance.put(`/auth/update?name=`+infoData).then(()=>
            window.alert("수정완료!!"));
}

function createComment(commentData, postId){
    return instance.post(`/comments/`+postId, commentData);
}

function createRoom(roomData) {
    return instance.post(`/webinar/create`,roomData);
}

function mainPinned(id) {
    return instance.post(`/webinar/main/`+ id).then(()=>
    window.alert("웨비나 메인등록!"))
}

function deleteRoom(id) {
    return instance.delete(`/webinar/delete/`+id).then(()=>
    window.alert("웨비나 삭제!"))
}

export { registerUser, signInUser, createPost, getMyInfoApi, updatePost, deletePost, updateMyPage, createComment, createRoom, mainPinned, deleteRoom};