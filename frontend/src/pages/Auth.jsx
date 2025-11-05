import React, { useState } from 'react';
import api from '../api/axiosConfig';
import Modal from '../components/Modal.jsx';

const Auth = ({ onAuth }) => {
  const [form, setForm] = useState({ username:'', password:'', role:'STUDENT' });
  const [modal, setModal] = useState({open:false, title:'', body:''});

  const register = async () => {
    try {
      const res = await api.post('/auth/register', form);
      onAuth(res.data);
    } catch (e) {
      setModal({open:true, title:'Error', body:'Registro fallido: ' + (e.response?.status || '')});
    }
  }

  const login = async () => {
    try {
      const res = await api.post('/auth/login', form);
      onAuth(res.data);
    } catch (e) {
      setModal({open:true, title:'Error', body:'Login fallido'});
    }
  }

  return (
    <section className="auth">
      <h2>Registrarse / Iniciar sesión</h2>
      <label>Usuario<input value={form.username} onChange={e=>setForm({...form, username:e.target.value})} /></label>
      <label>Contraseña<input type="password" value={form.password} onChange={e=>setForm({...form, password:e.target.value})} /></label>
      <div className="actions">
        <button className="btn" onClick={register}>Registrarse</button>
        <button className="btn" onClick={login}>Iniciar sesión</button>
      </div>

      {modal.open && <Modal title={modal.title} onClose={()=>setModal({open:false})}>{modal.body}</Modal>}
    </section>
  );
}

export default Auth;