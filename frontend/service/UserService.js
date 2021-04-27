export default class UserService {

    constructor(axios) {
        this.http = axios;
    }

    save(values) {
        if (values.id == null || values.id == '') {
            return this.http.$post('/user', values)
        } else {
            return this.http.$put('/user/' + values.id, values)
        }
    }

    list() {
        return this.http.$get('/user')
    }

    byId(id) {
        return this.http.$get('/user/' + id)
    }

    delete(id) {
        return this.http.$delete('/user/' + id)
    }

}