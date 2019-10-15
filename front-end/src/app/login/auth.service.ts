import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';

import { Usuario } from '../auth/usuario';
import { ClientService } from '../auth/client.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado = false;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private client: ClientService) { }

  fazerLogin(usuario: Usuario) {
    if (this.client.getByNomeAndSenha(usuario)) {
      this.usuarioAutenticado = true;
      this.mostrarMenuEmitter.emit(true);
      this.router.navigate(['/']);
    } else {
      this.usuarioAutenticado = false;
      this.mostrarMenuEmitter.emit(false);
    }
  }

  isUsuarioAutenticado() {
    return this.usuarioAutenticado;
  }

}
