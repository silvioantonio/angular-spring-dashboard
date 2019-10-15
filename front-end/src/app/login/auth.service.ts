import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

import { Usuario } from '../usuario';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private usuarioAutenticado = false;

  apiClient = 'http://localhost:8080/';

  username: string;
  password: string;

  mostrarMenuEmitter = new EventEmitter<boolean>();

  constructor(private router: Router, private httpClient: HttpClient) { }

  fazerLogin(usuario: Usuario) {
    username = this.httpClient.get
    if(usuario.nome === )
  }

}
