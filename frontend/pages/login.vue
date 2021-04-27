<template>
  <div class="area-login">
    <Toast/>
    <ValidationObserver v-slot="{ handleSubmit }">
      <form @submit.prevent="handleSubmit(entrar)">
        <Card>
          <template slot="header">
            <div class="area-header">
              <h1>Login</h1>
            </div>  
          </template>
          <template slot="content">
            <div class="p-fluid">
              <div class="p-field">
                <ValidationProvider name="Usuário" rules="required" v-slot="{ errors }">
                  <span class="p-input-icon-left">
                    <i class="pi pi-user" />
                    <InputText v-model="login.login" type="text" autofocus placeholder="Login" />
                  </span>
                  <span v-show="errors.length > 0" class="p-invalid">{{ errors[0] }}</span>
                </ValidationProvider>
              </div>
              <div class="p-field">
                <ValidationProvider name="Senha" rules="required" v-slot="{ errors }">
                  <span class="p-input-icon-left">
                    <i class="pi pi-lock" />
                    <InputText v-model="login.password" type="password" placeholder="Senha" />
                  </span>
                  <span v-show="errors.length > 0" class="p-invalid">{{ errors[0]}}</span>
                </ValidationProvider>
              </div>
              <div class="p-field div-btn">
                <Button type="submit" class="btn-login" label="Entrar" />
              </div>
            </div>
          </template>
        </Card>
      </form>
    </ValidationObserver>
  </div>
</template>

<script>
export default {
    middleware: 'guest',
    layout: 'branco',
    data() {
        return {
            login: {
                login: null,
                password: null
            }
        }
    },
    methods: {
        async entrar() {
            try {
                let response = await this.$auth.loginWith('local', { data: this.login })

                if (response.status == 200) {
                    window.location.href = '/';
                } else {
                    this.$toast.add({severity:'error', detail: 'Dados icorretos. Tente novamente.', life: 3000})
                }
            } catch (err) {
                this.$toast.add({severity:'error', detail: 'Dados icorretos. Tente novamente.', life: 3000})
            }
        }
    }
}
</script>


<style @scoped>

.area-login .p-fluid {
  background-color: #f2f2f2;
  padding: 15px 10px;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  position: absolute;
  top: 90px;
  width: 325px;
}

.area-login .p-card {
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  -webkit-box-shadow: 0px 4px 8px 1px rgba(0,0,0,0.25);
  -moz-box-shadow: 0px 4px 8px 1px rgba(0,0,0,0.25);
  box-shadow: 0px 4px 8px 1px rgba(0,0,0,0.25);
  position: relative;
  height: 370px;
}

.div-btn {
  margin: 0 auto;
  width: 122px !important; 
}

.area-header {
  margin: 0 auto;
  width: 201px;
  padding-top: 15px;
}
 
.area-login img {
  width: 60px !important;
  margin-right: 10px;
  float: left;
}
.area-login h1 {
  float: left;
  color: #fff;
  font-size: 20px;
}
.area-login .p-card-header {
  background-color: #5b5784 !important;
  width: 100%;
  height: 120px !important;  
  -webkit-border-radius: 10px 10px 0px 0px;
  -moz-border-radius: 10px 10px 0px 0px;
  border-radius: 10px 10px 0px 0px;
}
.area-login {
  width: 351px;
  height: 383px;
  margin: 150px auto;
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
}
.login-form-forgot {
  float: right;
}
.area-login .btn-login {
  -webkit-border-radius: 10px;
  -moz-border-radius: 10px;
  border-radius: 10px;
  background-color: #004888;
  border-color: #004888;
  margin: 20px auto;
  width: 122px !important; 
}

.area-login .btn-login:hover {
  background-color: #004888;
  border-color: #004888;
}

.area-login .p-inputtext {
  background-color: #f2f2f2;
  border: none;
  border-bottom: 1px solid #B5B2B2;
  -webkit-border-radius: 0px;
  -moz-border-radius: 0px;
  border-radius: 0px;
  opacity: 0.5;
}

.area-login .p-field {
  margin-top: 20px !important;
}

</style>