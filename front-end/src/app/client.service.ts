import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Usuario } from './usuario';

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  url = 'http://localhost:8080/';

  constructor(private httpClient: HttpClient) { }

  getAll() {
    return this.httpClient.get<Usuario[]>(this.url);
  }

  getById(id: number) {
    return this.httpClient.get<Usuario>(this.url + '/' + id);
  }

  create(usuario: Usuario) {
    return this.httpClient.post(this.url, usuario);
  }

  update(usuario: Usuario) {
    return this.httpClient.put(this.url + '/' + usuario.id, usuario);
  }

  delete(id: number) {
    return this.httpClient.delete(this.url + '/' + id);
  }

}
