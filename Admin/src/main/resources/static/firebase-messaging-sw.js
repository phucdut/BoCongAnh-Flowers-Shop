importScripts('https://www.gstatic.com/firebasejs/8.1.1/firebase-app.js');
importScripts('https://www.gstatic.com/firebasejs/8.1.1/firebase-messaging.js');

const firebaseConfig = {
    apiKey: "AIzaSyCVXKrg2DJsKg8k2zXi7yf0EaR1NCZeDKE",
    authDomain: "flowershop-a8b0c.firebaseapp.com",
    projectId: "flowershop-a8b0c",
    storageBucket: "flowershop-a8b0c.appspot.com",
    messagingSenderId: "97871611471",
    appId: "1:97871611471:web:79776792d32efabbaa4c3a",
    measurementId: "G-99S5X9JCX0"
};
firebase.initializeApp(firebaseConfig);

firebase.messaging();

firebase.messaging().onBackgroundMessage(function (payload) {
    console.log('Received background message ', payload);
    const notificationTitle = payload.notification.title;
    const notificationOptions = {
        body: payload.notification.body,
        icon: 'http://103.153.72.198:8080/images/Pink Watercolour Flower Shop Logo (2).png',
    };
    return self.registration.showNotification(notificationTitle, notificationOptions);
});



