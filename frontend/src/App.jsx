import React, { useState, useEffect } from 'react';
import Header from './components/Header.jsx';
import Footer from './components/Footer.jsx';
import Home from './pages/Home.jsx';
import Auth from './pages/Auth.jsx';
import Profile from './pages/Profile.jsx';
import Admin from './pages/Admin.jsx';

const App = () => {
  const [page, setPage] = useState('home');
  const [user, setUser] = useState(null);

  useEffect(() => {
    // load user from localStorage
    const raw = localStorage.getItem('academy_user');
    if (raw) setUser(JSON.parse(raw));
  }, []);

  return (
    <div className="app">
      <Header onNavigate={setPage} user={user} setUser={setUser} />
      <main>
        {page === 'home' && <Home onNavigate={setPage} />}
        {page === 'auth' && <Auth onAuth={(u) => { setUser(u); localStorage.setItem('academy_user', JSON.stringify(u)); setPage('profile'); }} />}
        {page === 'profile' && <Profile user={user} onNavigate={setPage} setUser={setUser} />}
        {page === 'admin' && <Admin />}
      </main>
      <Footer />
    </div>
  );
}

export default App;
