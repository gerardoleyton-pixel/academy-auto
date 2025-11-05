import React from 'react';

const Modal = ({ title, children, onClose }) => {
  if (!title && !children) return null;
  return (
    <div className="modal-backdrop">
      <div className="modal">
        <div className="modal-header"><h3>{title}</h3></div>
        <div className="modal-body">{children}</div>
        <div className="modal-footer">
          <button className="btn" onClick={onClose}>Cerrar</button>
        </div>
      </div>
    </div>
  );
}

export default Modal;