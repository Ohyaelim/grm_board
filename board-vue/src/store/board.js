import Vue from "vue"
import {router} from "@/routes/route";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        boardInfo: null
    },
    mutations: {

    },
    actions: {
        boardWrite(){
            //글 작성
            axios
                .post('post/{boardId})
        }
    }
})

