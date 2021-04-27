<template>
    <div>
        <h1>Cadastro de Usuário</h1>

        <nuxt-link class="p-button p-button-text" to="/user">Listagem de Usuário</nuxt-link>

        <ValidationObserver ref="form">
            <form @submit.prevent="save()">        
                <div class="p-fluid">
                    <div class="p-field">
                        <ValidationProvider name="nome" rules="required" v-slot="{errors}">
                            <label for="name">Nome</label>
                            <InputText id="name" v-model="user.name" type="text" />
                            <span v-show="errors.length > 0">{{ errors[0] }}</span>
                        </ValidationProvider>
                    </div>
                    <div class="p-field">
                        <ValidationProvider name="login" rules="required" v-slot="{errors}">
                            <label for="login">Login</label>
                            <InputText id="login" v-model="user.login" type="text" />
                            <span v-show="errors.length > 0">{{ errors[0] }}</span>
                        </ValidationProvider>
                    </div>
                    <div class="p-field">
                        <ValidationProvider name="senha" rules="required" v-slot="{errors}">
                            <label for="password">Senha</label>
                            <InputText id="password" v-model="user.password" type="password" />
                            <span v-show="errors.length > 0">{{ errors[0] }}</span>
                        </ValidationProvider>
                    </div>
                </div>

                <Button type="submit" class="p-button-secondary" label="Salvar" />
            </form>
        </ValidationObserver>    

    </div>
</template>

<script>
import UserService from '~/service/UserService.js';

export default {
    middleware: "auth",
    layout: "default",
    async asyncData({ params, error }) {
        return {
            id: params.id,
            user: {
                id: null,
                name: '',
                login: '',
                password: ''
            }
        }    
    },
    userService: null,
    created() {
        this.userService = new UserService(this.$axios);
    },
    mounted() {
        if (this.id != "new") {
            this.getUser(this.id);
        }    
    },
    methods: {
        async getUser(id) {
            let response = await this.userService.byId(id);
            if (response.id) {
                this.user = response;
            }
        },
        async save() {
            this.$refs.form.validate().then((success) =>{
                if (!success) {
                    return;
                }

                let values = this.user;

                this.userService.save(values).then((response) => {
                    this.$toast.add({ severity: "success", detail: "Usuário foi salvo", life: 5000 });
                    this.$router.push("/user");
                })
                .catch((error) => {
                    this.$toast.add({ severity: "error", detail: "Ocorreu um erro ao salvar o usuário.", life: 5000 });            
                });
            });
        }
    }
}
</script>
