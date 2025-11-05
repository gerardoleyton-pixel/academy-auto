import React from 'react';

const Header = ({ onNavigate, user, setUser }) => {
  return (
    <header className="site-header">
      <div className="brand" onClick={() => onNavigate('home')}>Academy</div>
      <nav>
        <button onClick={() => onNavigate('home')}>Cursos</button>
        {!user && <button onClick={() => onNavigate('auth')}>Registrarse / Iniciar</button>}
        {user && <>
          <button onClick={() => onNavigate('profile')}>Perfil</button>
          {user.role === 'ADMIN' && <button onClick={() => onNavigate('admin')}>Admin</button>}
          <button onClick={() => { localStorage.removeItem('academy_user'); setUser(null); onNavigate('home'); }}>Cerrar</button>
        </>}
      </nav>
    </header>
  );
}

export default Header;