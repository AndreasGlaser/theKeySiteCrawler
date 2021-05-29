import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {Blog} from "../blog";
import {BlogService} from "../blog-service.service";

@Component({
  selector: 'app-blog',
  templateUrl: './blog.component.html',
  styleUrls: ['./blog.component.scss']
})
export class BlogComponent implements OnInit {
  blog: Blog | undefined;

  constructor(private route: ActivatedRoute, private blogService: BlogService) { }

  ngOnInit(): void {
    const routeParams = this.route.snapshot.paramMap;
    const idFromRoute = Number(routeParams.get('blogId'));

    this.blogService.find(idFromRoute).subscribe(data => {
      this.blog = data;
    });
  }

}
