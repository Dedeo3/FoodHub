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

const loginData = async (req, res) => {
  try {
    const queryGet = "SELECT * FROM customer";
    const result = await new Promise((resolve, reject) => {
      db.all(queryGet, (error, result) => {
        if (error) reject(error);
        resolve(result);
      });
    });
    res.json(result);
  } catch (error) {
    console.error(error);
    return res.status(500).json({ message: "Terjadi kesalahan" });
  }
};

const loginValidation = async (req, res) => {
  try {
    // Memperbaiki query SQL
    const nameQuery = `SELECT customerName FROM customer WHERE customerName="${req.body.customerName}"`;
    const pwQuery = `SELECT password FROM customer WHERE password='${req.body.password}'`;

    // Mengambil hasil untuk customerName
    const customerNameResult = await new Promise((resolve, reject) => {
      db.all(nameQuery, (error, result) => {
        if (error) reject(error);
        resolve(result);
      });
    });

    // Mengambil hasil untuk password
    const customerPasswordResult = await new Promise((resolve, reject) => {
      db.all(pwQuery, (error, result) => {
        if (error) reject(error);
        resolve(result);
      });
    });

    console.log(customerNameResult[0].customerName, customerPasswordResult[0].password);
    console.log(req.body.customerName, req.body.password);

    // Memeriksa apakah ada hasil yang sesuai
    if (customerNameResult.length > 0 && customerPasswordResult.length > 0) {
      if(req.body.customerName == customerNameResult[0].customerName && req.body.password == customerPasswordResult[0].password){
        return res.status(200).json('login sukses');
      }else{
        return res.status(400).json('gagal login');
      }
      
    } else {
      return res.status(500).json('cannot find data');
    }
  } catch (error) {
    console.error(error);
    return res.status(500).json({ message: "Terjadi kesalahan" });
  }
};


module.exports = { loginData, loginValidation };
