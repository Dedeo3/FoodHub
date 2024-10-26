const express= require('express')
const route= express.Router();

const res= require('../controller/restaurantController')

route.get('/',res.listRestaurant)
route.post('/',res.dataByid)

module.exports= route