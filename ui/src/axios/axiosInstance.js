import axios from 'axios'

const Axios = axios.create({
    /* 基础路径 */
    baseURL: 'http://localhost:38080',
    /* 设置超时时间 */
    timeout: 10000000,
    withCredentials: true
});

export default Axios