const express= require('express')
const route= express.Router();

const res= require('../controller/loginController')

route.get('/',res.loginData)
route.post('/',res.loginValidation)

module.exports= route