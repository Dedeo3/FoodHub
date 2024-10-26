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

const listCategory = async (req, res) => {
  const queryGet = "SELECT * FROM category ";
  const result = await new Promise((resolve, reject) => {
    db.all(queryGet, (error, result) => {
      if (error) reject(error);
      resolve(result);
    });
  });
  return res.status(200).json(result);
};

const location = async (req, res) => {
  const queryGet = "SELECT * FROM location ";
  const result = await new Promise((resolve, reject) => {
    db.all(queryGet, (error, result) => {
      if (error) reject(error);
      resolve(result);
    });
  });
  return res.status(200).json(result);
};
const status = async (req, res) => {
  const queryGet = "SELECT * FROM status ";
  const result = await new Promise((resolve, reject) => {
    db.all(queryGet, (error, result) => {
      if (error) reject(error);
      resolve(result);
    });
  });
  return res.status(200).json(result);
};

const wallet = async (req, res) => {
  const queryGet = "SELECT * FROM wallet ";
  const result = await new Promise((resolve, reject) => {
    db.all(queryGet, (error, result) => {
      if (error) reject(error);
      resolve(result);
    });
  });
  return res.status(200).json(result);
};

const listMenu = async (req, res) => {
  const queryGet =
    "SELECT listMenu.* , restaurant.restaurantName, category.categoryName FROM listMenu INNER JOIN category on category.idCategory=listMenu.idCategory INNER JOIN restaurant on restaurant.idRestaurant=listMenu.idRestaurant";
  const result = await new Promise((resolve, reject) => {
    db.all(queryGet, (error, result) => {
      if (error) reject(error);
      resolve(result);
    });
  });
  return res.status(200).json(result);
};
const buy = async (req, res) => {
  const { idWallet, sisaSaldo } = req.body;

  // Pastikan input tidak kosong
  if (!idWallet || sisaSaldo === undefined) {
    return res
      .status(400)
      .json({ message: "idwallet dan sisaSaldo diperlukan." });
  }

  // Memperbaiki query SQL
  const queryUpdate = `UPDATE wallet SET saldo = ? WHERE idWallet = ?`;

  // Mengupdate saldo wallet
  const result = await new Promise((resolve, reject) => {
    db.run(queryUpdate, [sisaSaldo, idWallet], function (error) {
      if (error) reject(error);
      resolve(this.changes); // Return the number of affected rows
    });
  });

  // Memeriksa apakah ada baris yang terpengaruh
  if (result > 0) {
    res.json({ message: `success edit ${idWallet}` });
  } else {
    return res.status(404).json({ message: "Data tidak ditemukan" });
  }
};

const order = async (req, res) => {
  try {
    const { idMenu, idCustomer } = req.body;

    // Set status awal (1)
    const initialStatus = 1;
    const currentTime = new Date().toISOString();

    // Query untuk insert
    const queryInsert =
      "INSERT INTO orders (idStatus, idMenu, idCustomer, tglPemesanan) VALUES (?, ?, ?, ?)";

    // Lakukan insert dan dapatkan ID yang baru diinsert
    const result = await new Promise((resolve, reject) => {
      db.run(
        queryInsert,
        [initialStatus, idMenu, idCustomer, currentTime],
        function (error) {
          if (error) reject(error);
          resolve({ id: this.lastID }); // Simpan ID untuk update nanti
        }
      );
    });

    // Tunggu 15 detik, lalu update status
    setTimeout(async () => {
      try {
        const queryUpdate = "UPDATE orders SET idStatus = ? WHERE idOrder = ?";
        const newStatus = 2;

        await new Promise((resolve, reject) => {
          db.run(queryUpdate, [newStatus, result.id], (error) => {
            if (error) {
              console.error("Error updating status:", error);
              reject(error);
            }
            console.log(`Order ${result.id} status updated to ${newStatus}`);
            resolve();
            return res.status(200).json("completed")
          });
        });
      } catch (updateError) {
        console.error("Error in delayed status update:", updateError);
      }
    }, 15000); //15 detik

    res.json({
      message: "success order",
      orderId: result.id,
      note: "Status will be updated to 2 after 15 seconds",
    });
  } catch (error) {
    console.error("Error in order process:", error);
    res.status(500).json({
      message: "Terjadi kesalahan saat memproses pesanan",
      error: error.message,
    });
  }
};


module.exports = {
  listCategory,
  location,
  status,
  wallet,
  listMenu,
  buy,
  order,
};