import { Component, OnInit } from '@angular/core';
import {BlogService} from "../blog-service.service";
import {Blog} from "../blog";

@Component({
  selector: 'app-blogs',
  templateUrl: './blogs.component.html',
  styleUrls: ['./blogs.component.scss']
})
export class BlogsComponent implements OnInit {

  blogs: Blog[] = [{id:"1", title:"title", content:"content", creationDate:0, lastUpdated:0}];

  constructor(private blogService: BlogService) { }

  ngOnInit(): void {
    this.blogService.findAll().subscribe(data => {
      this.blogs = data;
    });
  }

}
