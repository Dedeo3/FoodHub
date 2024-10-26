const sqlite3 = require('sqlite3').verbose();
const path = require('path');

// Dapatkan absolute path dari database
const dbPath = path.resolve(__dirname, '../data.db');

let db = new sqlite3.Database(dbPath, (err) => {
  if (err) {
    console.error("db cannot be open");
    console.error(err.message);
    throw err;
  } else {
    console.log("Database dibuka di lokasi:", dbPath);
    
    // Periksa ukuran file database
    const fs = require('fs');
    fs.stat(dbPath, (err, stats) => {
      if (err) {
        console.error("Error checking database file:", err);
      } else {
        console.log("Database file size:", stats.size, "bytes");
      }
    });

    // Periksa tabel yang ada di database
    db.all("SELECT name FROM sqlite_master WHERE type='table'", [], (err, tables) => {
      if (err) {
        console.error("Error checking tables:", err);
      } else {
        console.log("Daftar tabel dalam database:");
        tables.forEach(table => {
          console.log("-", table.name);
        });
      }
    });

    // Periksa versi SQLite
    db.get("SELECT sqlite_version()", [], (err, row) => {
      if (err) {
        console.error("Error checking SQLite version:", err);
      } else {
        console.log("SQLite Version:", row['sqlite_version()']);
      }
    });

    // Periksa mode akses database
    const mode = db.open ? "read/write" : "closed";
    console.log("Database access mode:", mode);
  }
});

// Handle database errors
db.on('error', (err) => {
  console.error('Database error:', err);
});

// Handle database close
db.on('close', () => {
  console.log('Database connection closed');
});

// Fungsi untuk mendapatkan informasi database
const getDatabaseInfo = async () => {
  return new Promise((resolve, reject) => {
    db.all(`
      SELECT 
        name as tableName,
        sql as tableSchema
      FROM sqlite_master 
      WHERE type='table'
      ORDER BY name;
    `, [], (err, tables) => {
      if (err) reject(err);
      resolve(tables);
    });
  });
};

// Export database dan fungsi utility
module.exports = {
  db,
  getDatabaseInfo,
  getDatabasePath: () => dbPath,
  checkConnection: async () => {
    try {
      await new Promise((resolve, reject) => {
        db.get("SELECT 1", [], (err) => {
          if (err) reject(err);
          resolve();
        });
      });
      return true;
    } catch (err) {
      console.error("Database connection check failed:", err);
      return false;
    }
  }
};