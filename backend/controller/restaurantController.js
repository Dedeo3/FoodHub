const {
  db,
  getDatabaseInfo,
  getDatabasePath,
  checkConnection,
} = require("../config/database");

const testConnection = async () => {
  try {
    // Periksa koneksi
    const isConnected = await checkConnection();
    console.log("Database connected:", isConnected);

    // Tampilkan path database
    console.log("Database path:", getDatabasePath());

    // Dapatkan informasi tabel
    const tables = await getDatabaseInfo();
    console.log("Database tables:");
    tables.forEach((table) => {
      console.log("\nTable:", table.tableName);
      // console.log("Schema:", table.tableSchema);
    });
  } catch (error) {
    console.error("Error testing database:", error);
  }
};

// Jalankan test
testConnection();

const listRestaurant= async (req,res)=>{
    const queryGet = "SELECT * FROM restaurant inner join category on restaurant.idCategory=category.idCategory";
    const result = await new Promise((resolve, reject) => {
      db.all(queryGet, (error, result) => {
        if (error) reject(error);
        resolve(result);
      });
    });
    return res.status(200).json(result);
}

const dataByid= async(req,res)=>{
    if(req.body){
        const queryGetbyId = `SELECT * FROM restaurant inner join category on restaurant.idCategory=category.idCategory where idRestaurant= ${req.body.idRestaurant}`;
        const result = await new Promise((resolve, reject) => {
            db.all(queryGetbyId, (error, result) => {
              if (error) reject(error);
              resolve(result);
            });
          });
          res.status(200).json(result);
    }
}

module.exports={listRestaurant,dataByid}