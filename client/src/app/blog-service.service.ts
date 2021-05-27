import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Blog} from "./blog";

@Injectable({
  providedIn: 'root'
})
export class BlogService {

  private readonly blogsUrl: string;

  constructor(private http: HttpClient) {
    this.blogsUrl = 'http://localhost:8080/blogs/';
  }

  public findAll(): Observable<Blog[]> {
    return this.http.get<Blog[]>(this.blogsUrl);
  }

  public read(blog: Blog) {
    return this.http.get<Blog>(this.blogsUrl + blog.id);
  }
}
