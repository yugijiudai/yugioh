<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>

        function openMe() {
            openDB(myDB.name, myDB.version);
            console.log("打开成功");
        }

        function addMe() {
            setTimeout(function () {
                addData(myDB.db, "students");
                console.log("插入成功");
            }, 500);
        }

        function deleteDB(db, name) {
            db.deleteDatabase(name);
            console.log("删除成功");
        }

        function openDB(name, version) {
            var indexedDB = window.indexedDB || window.webkitIndexedDB ||
                    window.mozIndexedDB;

            if ('webkitIndexedDB' in window) {
                window.IDBTransaction = window.webkitIDBTransaction;
                window.IDBKeyRange = window.webkitIDBKeyRange;
            }
            var request = indexedDB.open(name, myDB.version);
            request.onerror = function (e) {
                console.log(e.currentTarget.error.message);
            };
            request.onsuccess = function (e) {
                myDB.db = e.target.result;
            };
            request.onupgradeneeded = function (e) {
                var db = e.target.result;
                if (!db.objectStoreNames.contains('students')) {
                    db.createObjectStore('students', {keyPath: "id"});
                }
                console.log('DB version changed to ' + version);
            };
            return myDB.db;
        }

        function addData(db, storeName) {
            try {
                var transaction = db.transaction(storeName, 'readwrite');
                var store = transaction.objectStore(storeName);
                for (var i in students) {
                    store.add(students[i]);
                }
            }
            catch (e) {
                console.log(e);
                throw new Error(e);
            }

        }

        var myDB = {
            name: 'test',
            version: 1,
            db: null
        };


        var students = [{
            id: 1001,
            name: "Byron",
            age: 24
        }, {
            id: 1002,
            name: "Frank",
            age: 30
        }, {
            id: 1003,
            name: "Aaron",
            age: 26
        }];

    </script>
</head>
<button onclick="openMe();">打开</button>
<button onclick="addMe();">保存</button>
<button onclick="deleteDB(window.indexedDB,myDB.name);">关闭</button>
<body>
</body>
</html>
