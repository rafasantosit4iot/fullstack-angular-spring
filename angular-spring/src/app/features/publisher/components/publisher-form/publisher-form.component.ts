import { Component, computed, effect, inject } from '@angular/core';
import { PublisherService } from '../../services/publisher.service';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { PublisherCreateBody } from '../../models/publisher';

@Component({
  selector: 'app-publisher-form',
  imports: [ReactiveFormsModule],
  templateUrl: './publisher-form.component.html',
  styleUrl: './publisher-form.component.css'
})
export class PublisherFormComponent {
  // INJEÇÃO DE DEPENDÊNCIAS
  private formBuilder = inject(FormBuilder);
  private publisherService = inject(PublisherService);

  // SIGNALS
  public loading = computed(() => this.publisherService.loading());
  public error = computed(() => this.publisherService.error());
  public success = computed(() => this.publisherService.success());
  public operationMessage = computed(() => this.publisherService.operationMessage());

  // FORMULÁRIO
  public publisherForm = this.formBuilder.group({
    name: ['', [Validators.required, Validators.maxLength(100)]],
    founder: ['', [Validators.required, Validators.maxLength(100)]],
    foundationYear: [0, [Validators.required, Validators.max(new Date().getFullYear()), Validators.min(1500)]]
  })

  constructor() {
    effect(() => {
      const messages = this.operationMessage();
      if (messages.length > 0) {
        console.log(messages);
      }
      if (this.success()) {
        this.publisherForm.reset();
      }
    });
  }

  onPageChange(newPagenumber: number) {
    this.publisherService.setPageNumber(newPagenumber);
  }

  createPublisher() {
    this.publisherService.createPublisher(this.publisherForm.value as PublisherCreateBody);
  }
}
